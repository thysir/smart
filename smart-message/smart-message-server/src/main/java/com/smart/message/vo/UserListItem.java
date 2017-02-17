package com.smart.message.vo;

import com.smart.message.model.User;

public class UserListItem extends User{

	private static final long serialVersionUID = -1511914642972133577L;

	private String applicationNames;

	public String getApplicationNames() {
		return applicationNames;
	}

	public void setApplicationNames(String applicationNames) {
		this.applicationNames = applicationNames;
	}
}