package com.smart.mvc.model.mybatis;

public class Property {

	/** 键 */
	private String key;
	/** 值 */
	private Object value;

	public Property(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
