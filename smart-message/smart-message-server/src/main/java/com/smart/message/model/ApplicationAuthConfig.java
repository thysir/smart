package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class ApplicationAuthConfig extends PersistentObject {

	private static final long serialVersionUID = 4757736684438879503L;
	
	/** 应用类型id */
	private Integer applicationTypeId;
	/** 授权key */
	private String authKey;
	/** 描述 */
	private String description;

	public Integer getApplicationTypeId() {
		return applicationTypeId;
	}

	public void setApplicationTypeId(Integer applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
