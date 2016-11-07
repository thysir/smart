package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class GipcShotMessageApplication extends PersistentObject {

	private static final long serialVersionUID = 4404245551226628284L;
	/** 消息应用id */
	private String applicationId;
	/** 短信网关地址 */
	private String host;
	/** 接口名称 */
	private String appId;
	/** 登录接口用户名 */
	private String name;
	/** 登录接口密码 */
	private String pwd;
	/** 数据名称 */
	private String dbName;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
