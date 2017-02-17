package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class UserApplication extends PersistentObject {

	private static final long serialVersionUID = 1748989305997095011L;

	/** 用户id */
	private Integer userId;
	/** 应用id */
	private Integer applicationId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

}