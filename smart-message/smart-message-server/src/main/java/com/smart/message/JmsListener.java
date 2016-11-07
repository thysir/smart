package com.smart.message;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smart.message.util.ApplicationUtils;
import com.smart.util.StringUtils;

/**
 * <b>Description:jms发送消息监听</b><br>
 * @author jeason
 */
public class JmsListener implements MessageListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JmsListener.class);
	
	private List<MessageApplication> messageApplicationList=null;
	
	@Override
	public void onMessage(Message message) {
	
		List<com.smart.message.core.Message> messageList= null;
		try {
			String messageText=((TextMessage) message).getText();
			if(StringUtils.isNotBlank(messageText)){
				Object jsonObject=JSON.parse(messageText);
				if(jsonObject instanceof JSONArray){
					messageList=JSON.parseArray(messageText, com.smart.message.core.Message.class);
				}else{
					messageList=new ArrayList<com.smart.message.core.Message>(1);
					messageList.add(JSON.parseObject(messageText, com.smart.message.core.Message.class));
				}
			}
		}catch (JMSException e) {
			LOGGER.error("Jms illegal message!"); 
		}

		if(CollectionUtils.isEmpty(messageList)){
			for(com.smart.message.core.Message m:messageList){
				send(m.getContent(), m.getReceiver(), m.getApplicationId());
			}
		}
	}
	
	/**
	 * Description:调用匹配的应用发送消息
	 * @author jeason
	 * @param content 消息内容
	 * @param receiver 消息的接受者
	 * @param applicationId 发送消息的应用id
	 */
	private void send(String content, String receiver, String applicationId) {
		
		Application receiveApplication=ApplicationUtils.getById(applicationId);
		if(StringUtils.isNotBlank(content) && StringUtils.isNotBlank(receiver) && receiveApplication!=null){
			if(!CollectionUtils.isEmpty(messageApplicationList)){
				for(MessageApplication app:messageApplicationList){
					if(app.support(receiveApplication.getType())){
						app.send(content, receiver, receiveApplication);
					}
				}
			}
		}
	}

	public void setMessageApplicationList(List<MessageApplication> messageApplicationList) {
		this.messageApplicationList = messageApplicationList;
	}
}
