package com.smart.message.application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.smart.message.ApplicationAuthInfo;
import com.smart.message.ApplicationInfo;
import com.smart.message.MessageApplication;
import com.smart.message.exception.InvokerErrorException;
import com.smart.message.invoker.HttpInvoker;
import com.smart.message.service.ApplicationAuthService;
import com.smart.message.util.EncryptUtil;
import com.smart.util.DateUtils;
import com.smart.util.StringUtils;

@Component("ucpaasShotMessageApplication")
public class UcpaasShotMessageApplication implements MessageApplication {
	
	@Resource private ApplicationAuthService applicationAuthService;

	@Override
	public boolean support(Integer applicationType) {
		return new Integer(2).equals(applicationType);
	}

	@Override
	public void send(String content,String receiver, ApplicationInfo receiveApplication) throws InvokerErrorException {
		
		if(StringUtils.isBlank(content) || receiveApplication==null){
			throw new InvokerErrorException("参数验证失败：content="+content+";receiveApplication="+receiveApplication);
		}
		
		ApplicationAuthInfo applicationAuthInfo=applicationAuthService.getApplicationAuthInfo(receiveApplication.getId());
		
		//获取相应的授权信息
		String accountSid=applicationAuthInfo.getValue("accountSid");
		String authToken=applicationAuthInfo.getValue("authToken");
		String appId=applicationAuthInfo.getValue("appId");
		String templateId=applicationAuthInfo.getValue("templateId");
		
		String timeStamp=DateUtils.format(new Date(), "yyyyMMddHHmmss");
		
		//配置请求头信息
		Map<String, Object> headres=new HashMap<String, Object>();
		headres.put("Accept", "application/json");
		headres.put("Content-Type", "application/json;charset=utf-8");
		headres.put("Authorization", getAuthorization(accountSid, timeStamp));
		
		//组装请求体内容
		TemplateSMS templateSMS=new TemplateSMS(appId, content, templateId, receiver);
		Map<String, Object> requestData=new HashMap<String, Object>();
		requestData.put("templateSMS", templateSMS);
		
		//开始调用
		HttpInvoker invoker=new HttpInvoker();
		String api=getApi(accountSid,authToken,timeStamp);
		
		String invokerResult=invoker.post(api, headres, JSON.toJSONString(requestData));
		UcpassResponse ucpassResponse=JSON.parseObject(invokerResult, UcpassResponse.class);
		if(ucpassResponse==null || !ucpassResponse.isSuccess()){
			throw new InvokerErrorException("接口调用失败："+invokerResult);
		}
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

	/**
	 * <b>Description:ucpass消息体</b><br>
	 * @author jeason
	 */
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
	
	/***
	 * <b>Description:ucpass响应体</b><br>
	 * @author jeason
	 */
	protected static class UcpassResponse{
		
		private UcpassRes resp;

		public UcpassRes getResp() {
			return resp;
		}

		public void setResp(UcpassRes resp) {
			this.resp = resp;
		}
		
		public boolean isSuccess() {
			if(resp!=null && "000000".equals(resp.getRespCode())){
				return true;
			}
			return false;
		}
		
		protected class UcpassRes{
			
			private String respCode;

			public String getRespCode() {
				return respCode;
			}

			public void setRespCode(String respCode) {
				this.respCode = respCode;
			}
		}
	}
}
