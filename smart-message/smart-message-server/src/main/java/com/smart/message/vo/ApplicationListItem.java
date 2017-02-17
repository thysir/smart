package com.smart.message.vo;

import com.smart.message.model.Application;

public class ApplicationListItem extends Application{

	private static final long serialVersionUID = 7987560498847625004L;
	
	private String applicationTypeName;

	public String getApplicationTypeName() {
		return applicationTypeName;
	}

	public void setApplicationTypeName(String applicationTypeName) {
		this.applicationTypeName = applicationTypeName;
	}
}
