package com.smart.message;


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
	 * Description:发送消息
	 * @author 唐海洋
	 * @Version 1.0 2016-9-22上午11:38:02 
	 * @param content 消息内容
	 * @param receiver 接收消息的用户
	 * @param receiveApplication 收到消息的应用
	 * @return
	 */
	boolean send(String content,String receiver, ApplicationInfo receiveApplication);
}
