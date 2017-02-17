package com.smart.message.model;

import com.smart.mvc.model.PersistentObject;

public class Application extends PersistentObject {

	private static final long serialVersionUID = -8530799225314629798L;
	
	/** 应用名称 */
	private String name;
	/** 应用描述 */
	private String description;
	/** 排序 */
	private Integer sort;
	/** 类型 */
	private Integer applicationTypeId;
	/** 应用调用次数 */
	private Integer count=0;
	/** 调用成功数 */
	private Integer successCount=0;
	/** 调用失败数 */
	private Integer errorCount=0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getApplicationTypeId() {
		return applicationTypeId;
	}

	public void setApplicationTypeId(Integer applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

}
