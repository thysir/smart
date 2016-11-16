package com.smart.message.core;

import java.io.Serializable;

/**
 * <b>Description:消息实体</b><br>
 * 
 * @author jeason
 */
public class Message implements Serializable {

	private static final long serialVersionUID = -8162679062899408598L;

	/** 消息体 */
	private String content;

	/** 接受者 */
	private String receiver;

	/** 将要收到消息的应用 */
	private Integer applicationId;

	public Message() {
		super();
	}

	public Message(String content, String receiver, Integer applicationId) {
		super();
		this.content = content;
		this.receiver = receiver;
		this.applicationId = applicationId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

}