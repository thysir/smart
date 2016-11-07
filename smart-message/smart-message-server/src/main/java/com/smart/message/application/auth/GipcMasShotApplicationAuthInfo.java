package com.smart.message.application.auth;

import com.smart.message.ApplicationAuthInfo;

/**
 * <b>Description:知识产权法院mas机短信接口</b><br>
 * 
 * @author jeason
 */
public class GipcMasShotApplicationAuthInfo implements ApplicationAuthInfo {

	private static final long serialVersionUID = -1274173478670842880L;

	private String host; // 短信网关地址
	private String apiId; // 接口名称
	private String name; // 登录接口用户名
	private String pwd; // 登录接口密码
	private String dbName; // 数据库名称
	
	public GipcMasShotApplicationAuthInfo() {
		super();
	}

	public GipcMasShotApplicationAuthInfo(String host, String apiId,
			String name, String pwd, String dbName) {
		super();
		this.host = host;
		this.apiId = apiId;
		this.name = name;
		this.pwd = pwd;
		this.dbName = dbName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
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
