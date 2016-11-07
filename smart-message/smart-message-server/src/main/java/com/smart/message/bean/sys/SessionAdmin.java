package com.smart.message.bean.sys;

/**
 * 登录Session用户(解耦)
 */
public class SessionAdmin {
	
	private Integer id;		//login表中的登陆用户id
	private String name;	//登陆账号或者登陆人名称

	public SessionAdmin(Integer id,String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
