package com.smart.message.application;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.smart.message.ApplicationAuthInfo;
import com.smart.message.ApplicationInfo;
import com.smart.message.MessageApplication;
import com.smart.message.exception.InvokerErrorException;
import com.smart.message.invoker.HttpInvoker;
import com.smart.message.service.ApplicationAuthService;
import com.smart.mvc.model.Result;
import com.smart.util.StringUtils;

@Service("gipcMasShotMessageApplication")
public class GipcMasShotMessageApplication implements MessageApplication{
	
	@Resource private ApplicationAuthService applicationAuthService;
	
	@Override
	public boolean support(Integer applicationType) {
		return new Integer(1).equals(applicationType);
	}

	@Override
	public void send(String content,String receiver, ApplicationInfo receiveApplication) throws InvokerErrorException {
		
		if(StringUtils.isBlank(content) || receiveApplication==null){
			throw new InvokerErrorException("参数验证失败：content="+content+";receiveApplication="+receiveApplication);
		}
		
		// api地址
		ApplicationAuthInfo applicationAuthInfo = applicationAuthService.getApplicationAuthInfo(receiveApplication.getId());
		String api = applicationAuthInfo.getValue("api");

		//开始调用
		Map<String, Object> postData=new HashMap<String, Object>();
		postData.put("content", content);
		postData.put("receiver", receiver);
		
		HttpInvoker invoker=new HttpInvoker();
		String invokerResult=invoker.post(api,postData);
		Result response=JSON.parseObject(invokerResult, Result.class);
		if(response==null || !response.isSuccess()){
			throw new InvokerErrorException("接口调用失败："+response);
		}
	}
}