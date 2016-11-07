package com.smart.message.application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.smart.message.Application;
import com.smart.message.MessageApplication;
import com.smart.message.application.auth.UcpaasShotMessageApplicationAuthInfo;
import com.smart.message.enums.ApplicationType;
import com.smart.message.invoker.HttpInvoker;
import com.smart.message.util.EncryptUtil;
import com.smart.mvc.exception.ApplicationException;
import com.smart.util.DateUtils;
import com.smart.util.StringUtils;

@Component("ucpaasShotMessageApplication")
public class UcpaasShotMessageApplication implements MessageApplication {

	@Override
	public boolean support(Integer applicationType) {
		return ApplicationType.UCPAAS_SHOT_MESSAGE.getValue().equals(applicationType);
	}

	@Override
	public boolean send(String content,String receiver, Application receiveApplication) {
		boolean r = false;
		if (StringUtils.isNotBlank(content) && receiveApplication != null && receiveApplication.getApplicationAuthInfo()!=null) {
			
			if(!(receiveApplication.getApplicationAuthInfo() instanceof UcpaasShotMessageApplicationAuthInfo)){
				throw new ApplicationException("应用授权信息类型错误,期望类型为:"+UcpaasShotMessageApplicationAuthInfo.class.getSimpleName()
						+",实际类型为:"+receiveApplication.getApplicationAuthInfo().getClass().getSimpleName());
			}
			
			String timeStamp=DateUtils.format(new Date(), "yyyyMMddHHmmss");
			UcpaasShotMessageApplicationAuthInfo applicationAuthInfo=(UcpaasShotMessageApplicationAuthInfo) receiveApplication.getApplicationAuthInfo();
			
			//配置请求头信息
			Map<String, Object> headres=new HashMap<String, Object>();
			headres.put("Accept", "application/json");
			headres.put("Content-Type", "application/json;charset=utf-8");
			headres.put("Authorization", getAuthorization(applicationAuthInfo.getAccountSid(), timeStamp));
			
			//组装请求体内容
			TemplateSMS templateSMS=new TemplateSMS(applicationAuthInfo.getAppId(), content, applicationAuthInfo.getTemplateId(), receiver);
			Map<String, Object> requestData=new HashMap<String, Object>();
			requestData.put("templateSMS", templateSMS);
			
			//开始调用
			HttpInvoker invoker=new HttpInvoker();
			String api=getApi(applicationAuthInfo.getAccountSid(),applicationAuthInfo.getAuthToken(),timeStamp);
			invoker.post(api, headres, JSON.toJSONString(requestData));
			
			r=true;
		}
		return r;
	}

	/**
	 * Description:获取接口授权地址信息
	 * 
	 * @author jeason
	 * @return
	 */
	private String getApi(String accountSid,String authToken,String timeStamp) {

		String softVersion = "2014-06-30";
		String sigParameter=null;
		try {
			sigParameter = EncryptUtil.md5Digest(accountSid + authToken+ timeStamp).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "https://api.ucpaas.com/" + softVersion + "/Accounts/"+ accountSid + "/Messages/templateSMS?sig=" + sigParameter;
	}

	/**
	 * Description:获取授权信息
	 * 
	 * @author jeason
	 * @return
	 */
	private String getAuthorization(String accountSid,String timeStamp) {

		return new String(Base64Utils.encode((accountSid + ":" + timeStamp).getBytes()));
	}

	protected class TemplateSMS {

		private String appId;
		private String param;
		private String templateId;
		private String to;
		
		public TemplateSMS(String appId, String param, String templateId,
				String to) {
			super();
			this.appId = appId;
			this.param = param;
			this.templateId = templateId;
			this.to = to;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}

		public String getTemplateId() {
			return templateId;
		}

		public void setTemplateId(String templateId) {
			this.templateId = templateId;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}
	}
}
