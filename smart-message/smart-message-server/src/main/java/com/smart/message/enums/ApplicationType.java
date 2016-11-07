package com.smart.message.enums;

import com.smart.mvc.model.EnumItemable;


/**
 * <b>Description:消息应用类型枚举</b><br>
 * @author jeason
 */
public enum ApplicationType implements EnumItemable<ApplicationType> {
	
	SHOT_MESSAGE("短消息",0),WECHAT_MESSAGE("微信模板消息",1),APP_MESSAGE("app消息",2),
	
	GIPC_MAS_SHOT_MESSAGE("知识产权法院mas机接口短消息",10),UCPAAS_SHOT_MESSAGE("云之讯平台短消息",11);
	
	private String label;
	private Integer value;
	
	private ApplicationType(String label,Integer value) {
		this.label=label;
		this.value=value;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
}
