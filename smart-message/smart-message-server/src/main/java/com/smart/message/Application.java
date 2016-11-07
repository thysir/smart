package com.smart.message;

import java.io.Serializable;

import org.springframework.util.Assert;

import com.smart.message.enums.ApplicationType;

/**
 * <b>Description:消息应用（定义消息应用）</b><br>
 * @author jeason
 */
public class Application implements Serializable{
	
	private static final long serialVersionUID = -8727771419892946818L;
	
	private String id;			//appid
	
	private Integer type;		//消息应用枚举
	
	private ApplicationAuthInfo applicationAuthInfo;	//应用授权信息
	
	public Application() {
		super();
	}

	public Application(ApplicationType type) {
		
		super();
		
		Assert.notNull(type, "应用消息类型不能为null");
		this.type = type.getValue();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
