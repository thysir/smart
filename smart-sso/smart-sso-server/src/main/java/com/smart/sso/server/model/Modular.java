package com.smart.sso.server.model;

import com.smart.mvc.model.PersistentObject;

/**
 * 应用下模块
 * 
 * @author jeason
 */
public class Modular extends PersistentObject {

	private static final long serialVersionUID = 7902814112969375973L;

	/** 应用id */
	private Integer appId;
	/** 名称 */
	private String name;
	/** 编码 */
	private String code;

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
