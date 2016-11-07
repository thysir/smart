package com.smart.message.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jasson.im.api.APIClient;
import com.smart.message.Application;
import com.smart.message.MessageApplication;
import com.smart.message.application.auth.GipcMasShotApplicationAuthInfo;
import com.smart.message.enums.ApplicationType;
import com.smart.mvc.exception.ApplicationException;
import com.smart.util.StringUtils;

@Service("gipcMasShotMessageApplication")
public class GipcMasShotMessageApplication implements MessageApplication{
	
	private static final Logger logger = LoggerFactory.getLogger(GipcMasShotMessageApplication.class);

	private APIClientPool handlerPool = new APIClientPool();
	
	@PreDestroy
	public void release(){
		if(handlerPool!=null){
			handlerPool.release();
		}
	}

	@Override
	public boolean support(Integer applicationType) {
		return ApplicationType.GIPC_MAS_SHOT_MESSAGE.getValue().equals(applicationType);
	}

	@Override
	public boolean send(String content,String receiver, Application receiveApplication) {
		
		boolean r=false;
		if (StringUtils.isNotBlank(content) && receiveApplication != null && receiveApplication.getApplicationAuthInfo()!=null) {
			
			if(!(receiveApplication.getApplicationAuthInfo() instanceof GipcMasShotApplicationAuthInfo)){
				throw new ApplicationException("应用授权信息类型错误,期望类型为:"+GipcMasShotApplicationAuthInfo.class.getSimpleName()
						+",实际类型为:"+receiveApplication.getApplicationAuthInfo().getClass().getSimpleName());
			}
			
			GipcMasShotApplicationAuthInfo applicationAuthInfo=(GipcMasShotApplicationAuthInfo) receiveApplication.getApplicationAuthInfo();
			
			APIClient handler=handlerPool.get(applicationAuthInfo.getHost(), applicationAuthInfo.getName(), 
					applicationAuthInfo.getPwd(), applicationAuthInfo.getApiId(), applicationAuthInfo.getDbName());
			
			String[] receivePhoneNumbers=new String[]{receiver};
			int result  = handler.sendSM(receivePhoneNumbers, content, new Double(Math.random()*10000000).intValue(),25);
			if(result == APIClient.IMAPI_SUCC){ 
				r=true;
				logger.info("发送成功");
			}else if(result == APIClient.IMAPI_INIT_ERR){
				logger.error("未初始化");
			}else if(result == APIClient.IMAPI_CONN_ERR){
				logger.error("数据库连接失败");
			}else if(result == APIClient.IMAPI_DATA_ERR){
				logger.error("参数错误");
			}else if(result == APIClient.IMAPI_DATA_TOOLONG){
				logger.error("消息内容太长");
			}else if(result == APIClient.IMAPI_INS_ERR){
				logger.error("数据库插入错误");
			}else{
				logger.error("出现其他错误,result="+result);
			}
		}
        return r;
	}
	
	
	protected class APIClientPool{

		private Map<String, APIClient> pool=new HashMap<String, APIClient>();
		
		public APIClient get(String host,String name,String pwd,String apiId,String dbName) {

			String key=host+name+pwd+apiId+dbName;
			APIClient handler=pool.get(key);
			if(handler==null){
				synchronized(this) {  
					if((handler = pool.get(key)) == null) {  
						handler=new APIClient();
						int connectRe = handler.init(host, name, pwd, apiId,dbName);
				        if(connectRe == APIClient.IMAPI_SUCC){
				        	pool.put(key, handler);
				        	logger.debug("知识产权法院mas机短信接口初始化成功");
				        }else if(connectRe == APIClient.IMAPI_CONN_ERR){
				        	throw new ApplicationException("知识产权法院mas机短信接口初始化失败:连接失败");
				        }else if(connectRe == APIClient.IMAPI_API_ERR){
				        	throw new ApplicationException("知识产权法院mas机短信接口初始化失败:apiID不存在");
				        }
		           }  
		        }  
			}
	        return handler;
		}
		
		public void release() {
			
			Set<Entry<String, APIClient>> entrySet=pool.entrySet();
			if(entrySet!=null){
				for(Entry<String, APIClient> entry:entrySet){
					entry.getValue().release();
				}
			}
		}
	}
}