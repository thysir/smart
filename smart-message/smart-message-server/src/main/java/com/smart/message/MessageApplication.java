package com.smart.message;

import com.smart.message.exception.InvokerErrorException;

/**
 * <b>Description:发送消息应用接口</b><br>
 * @author jeason
 */
public interface MessageApplication{
	
	/**
	 * Description:判断该应用是否支持发送指定类型的消息
	 * @author jeason
	 * @param applicationType 应用类型
	 * @return
	 */
	boolean support(Integer applicationType);
	
	/**
	 * Description:
	 * @author jeason
 	 * @param content 消息内容
	 * @param receiver 接收消息的用户
	 * @param receiveApplication 收到消息的应用
	 * @throws InvokerErrorException 调用失败时抛出此异常
	 */
	void send(String content,String receiver, ApplicationInfo receiveApplication) throws InvokerErrorException;
}
