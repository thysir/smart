package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class User extends PersistentObject {

	private static final long serialVersionUID = 1748989305997095011L;

	/** 账号 */
	private String account;
	/** 密码 */
	private String password;
	/** 用户名称 */
	private String name;
	/**是否禁用*/
	private Boolean disable=true;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}
}