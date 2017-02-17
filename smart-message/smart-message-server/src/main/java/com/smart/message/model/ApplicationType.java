package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class ApplicationType extends PersistentObject {

	private static final long serialVersionUID = -8530799225314629798L;

	/** 类型名称 */
	private String name;
	/** 类型的key */
	private int type;
	/** 描述 */
	private String description;
	/** 排序 */
	private int sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
