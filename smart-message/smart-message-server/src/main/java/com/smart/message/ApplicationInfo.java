package com.smart.message;

import java.io.Serializable;

/**
 * <b>Description:消息应用（定义消息应用）</b><br>
 * @author jeason
 */
public class ApplicationInfo implements Serializable{
	
	private static final long serialVersionUID = -8727771419892946818L;
	
	private Integer id;			//appid
	
	private Integer type;		//消息应用枚举
	
	private ApplicationAuthInfo applicationAuthInfo;	//应用授权信息
	
	public ApplicationInfo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ApplicationAuthInfo getApplicationAuthInfo() {
		return applicationAuthInfo;
	}

	public void setApplicationAuthInfo(ApplicationAuthInfo applicationAuthInfo) {
		this.applicationAuthInfo = applicationAuthInfo;
	}
}
