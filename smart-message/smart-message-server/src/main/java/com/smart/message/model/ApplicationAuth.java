package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class ApplicationAuth extends PersistentObject {

	private static final long serialVersionUID = -8530799225314629798L;

	/** 应用id */
	private Integer applicationId;
	/** 授权的key */
	private String authKey;
	/** 授权的value */
	private String authValue;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

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
}