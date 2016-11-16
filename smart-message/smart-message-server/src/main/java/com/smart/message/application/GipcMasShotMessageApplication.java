package com.smart.message.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smart.message.ApplicationInfo;
import com.smart.message.MessageApplication;
import com.smart.message.invoker.HttpInvoker;
import com.smart.mvc.model.Result;
import com.smart.util.StringUtils;

@Service("gipcMasShotMessageApplication")
public class GipcMasShotMessageApplication implements MessageApplication{
	
	@Override
	public boolean support(Integer applicationType) {
		return new Integer(1).equals(applicationType);
	}

	@Override
	public boolean send(String content,String receiver, ApplicationInfo receiveApplication) {
		
		boolean r=false;
		if (StringUtils.isNotBlank(content) && receiveApplication != null) {

			//开始调用
			Map<String, Object> postData=new HashMap<String, Object>();
			postData.put("content", content);
			postData.put("receiver", receiver);
			
			try{
				HttpInvoker invoker=new HttpInvoker();
				String invokerResult=invoker.post(getApi(),postData);
				Result response=JSON.parseObject(invokerResult, Result.class);
				if(response!=null && response.isSuccess()){
					r=true;
				}
			}catch (Exception e) {}
		}
        return r;
	}
	
	/**
	 * Description:获取接口授权地址信息
	 * 
	 * @author jeason
	 * @return
	 */
	private String getApi() {

		return "http://14.23.155.206/gipc-message/message/send";
	}
}