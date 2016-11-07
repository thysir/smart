package com.smart.message.model;

import java.util.Date;

import com.smart.mvc.model.PersistentObject;

public class ApplicationCallLog extends PersistentObject {

	private static final long serialVersionUID = 4408541847865337891L;
	/** 消息应用id */
	private String applicationId;
	/** 接收者 */
	private String receiver;
	/** 消息体 */
	private String content;
	/** 发送时间 */
	private Date createTime;
	/** 发送状态 */
	private Integer status;
	/** 日志信息 */
	private String info;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
