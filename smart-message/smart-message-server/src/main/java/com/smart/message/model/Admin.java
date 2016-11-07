package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class Admin extends PersistentObject {

	private static final long serialVersionUID = 1748989305997095011L;
	
	/** 账号 */
	private String account;
	/** 密码 */
	private String password;
	/** 昵称 */
	private String nikeName;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
}
