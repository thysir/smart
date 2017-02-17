package com.smart.message.vo;

/**
 * <b>Description:应用授权项</b><br>
 * 
 * @author jeason
 */
public class ApplicationAuthItem {

	/** 授权的key */
	private String authKey;
	/** 授权的value */
	private String authValue;
	/** 字段描述 */
	private String description;

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getAuthValue() {
		return authValue;
	}

	public void setAuthValue(String authValue) {
		this.authValue = authValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
