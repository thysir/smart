package com.smart.message.application.auth;

import com.smart.message.ApplicationAuthInfo;

/**
 * <b>Description:云之讯短信接口</b><br>
 * @author jeason
 */
public class UcpaasShotMessageApplicationAuthInfo implements ApplicationAuthInfo {

	private static final long serialVersionUID = -1274173478670842880L;

	private String accountSid; // 账户Id
	private String authToken; // 账户授权令牌
	private String appId; // 应用id
	private String templateId; // 短信模板id
	
	public UcpaasShotMessageApplicationAuthInfo() {
		super();
	}

	public UcpaasShotMessageApplicationAuthInfo(String accountSid, String authToken,
			String appId, String templateId) {
		super();
		this.accountSid = accountSid;
		this.authToken = authToken;
		this.appId = appId;
		this.templateId = templateId;
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
