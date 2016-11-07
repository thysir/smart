package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class UcpaasShotMessageApplication extends PersistentObject {

	private static final long serialVersionUID = -5689232359659966471L;
	
	/** 消息应用id */
	private String applicationId;
	/** 账户Id */
	private String accountSid;
	/** 账户授权令牌 */
	private String authToken;
	/** 应用id */
	private String appId;
	/** 短信模板id */
	private String templateId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}
