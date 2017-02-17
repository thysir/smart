package com.smart.message.application;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.smart.message.ApplicationAuthInfo;
import com.smart.message.ApplicationInfo;
import com.smart.message.MessageApplication;
import com.smart.message.exception.InvokerErrorException;
import com.smart.message.service.ApplicationAuthService;
import com.smart.util.StringUtils;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

@Component("xgPushMessageApplication")
public class XgPushMessageApplication implements MessageApplication {

	@Resource private ApplicationAuthService applicationAuthService;

	@Override
	public boolean support(Integer applicationType) {
		return new Integer(3).equals(applicationType);
	}

	@Override
	public void send(String content, String receiver, ApplicationInfo receiveApplication) throws InvokerErrorException {
		
		if(StringUtils.isBlank(content) || receiveApplication==null){
			throw new InvokerErrorException("参数验证失败：content="+content+";receiveApplication="+receiveApplication);
		}
		
		ApplicationAuthInfo applicationAuthInfo = applicationAuthService.getApplicationAuthInfo(receiveApplication.getId());

		// android+ios授权信息
		String androidAccessId = applicationAuthInfo.getValue("android.accessId");
		String androidSecretKey = applicationAuthInfo.getValue("android.secretKey");
		String iosAccessId = applicationAuthInfo.getValue("ios.accessId");
		String iosSecretKey = applicationAuthInfo.getValue("ios.secretKey");

		boolean sendToAndroid=true,sendToIos=true;
		
		AppMessage appMessage=JSON.parseObject(content, AppMessage.class);
		if(appMessage.getType()!=null){
			sendToAndroid=AppMessage.TYPE_ANDROID==appMessage.getType()?true:false;
			sendToIos=AppMessage.TYPE_IOS==appMessage.getType()?true:false;
		}	
		
		//发送给安卓用户
		if(sendToAndroid){
			XingeApp xingeApp=new XingeApp(Long.parseLong(androidAccessId), androidSecretKey);
			Message message=createMessageAndroid(Message.TYPE_NOTIFICATION, appMessage.getTitle(), appMessage.getContent());
			if(StringUtils.isNotBlank(receiver)){
				JSONObject ret=xingeApp.pushSingleDevice(receiver, message);
				if(ret==null || ret.getInt("ret_code")!=0){
					throw new InvokerErrorException("接口调用失败："+ret);
				}
			}else{
				JSONObject ret=xingeApp.pushAllDevice(0, message);
				if(ret==null || ret.getInt("ret_code")!=0){
					throw new InvokerErrorException("接口调用失败："+ret);
				}
			}
		}
		
		//发送给ios用户
		if(sendToIos){
			XingeApp xingeApp=new XingeApp(Long.parseLong(iosAccessId), iosSecretKey);
			MessageIOS message=createMessageIos(MessageIOS.TYPE_APNS_NOTIFICATION, appMessage.getTitle(), appMessage.getContent());
			if(StringUtils.isNotBlank(receiver)){
				JSONObject ret=xingeApp.pushSingleDevice(receiver, message, XingeApp.IOSENV_PROD);
				if(ret==null || ret.getInt("ret_code")!=0){
					throw new InvokerErrorException("接口调用失败："+ret);
				}
			}else{
				JSONObject ret=xingeApp.pushAllDevice(0, message, XingeApp.IOSENV_PROD);
				if(ret==null || ret.getInt("ret_code")!=0){
					throw new InvokerErrorException("接口调用失败："+ret);
				}
			}
		}
	}

	/**
	 * Description:创建安卓的推送消息
	 * 
	 * @author jeason
	 * @return
	 */
	private Message createMessageAndroid(int messageType, String title,
			String content) {

		Message message = new Message();
		message.setType(messageType);

		// 定义通知消息如何展现 定义通知消息如何展现 定义通知消息如何展现
		Style style = new Style(1);
		style = new Style(3, 1, 1, 1, 0);

		// 设置点击时打开app
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_ACTIVITY);

		// 设置离线保存时间有效期
		TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);

		message.setTitle(title);
		message.setContent(content);
		message.setStyle(style);
		message.setAction(action);
		message.addAcceptTime(acceptTime1);

		return message;
	}
	
	/**
	 * Description:创建ios的推送消息
	 * 
	 * @author jeason
	 * @return
	 */
	private MessageIOS createMessageIos(int messageType, String title,
			String content) {
		
		MessageIOS messageIOS = new MessageIOS();
		
		// 设置离线保存时间有效期
	    TimeInterval acceptTime1 = new TimeInterval(0, 0, 23, 59);
        
        messageIOS.setType(messageType);
        messageIOS.setExpireTime(86400);
        messageIOS.setAlert("ios test");
        messageIOS.setBadge(1);
        messageIOS.setCategory("INVITE_CATEGORY");
        messageIOS.setSound("beep.wav");
        messageIOS.addAcceptTime(acceptTime1);
        
		return messageIOS;
	}

	protected static class AppMessage {

		public final static int TYPE_ANDROID = 0;
		public final static int TYPE_IOS = 1;

		// 消息类型，是发送给安卓客户端还是发送给ios客户端
		private Integer type;
		private String title;
		private String content;

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}
}
