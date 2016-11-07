package com.smart.message.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.smart.message.Application;
import com.smart.message.application.auth.GipcMasShotApplicationAuthInfo;
import com.smart.message.application.auth.UcpaasShotMessageApplicationAuthInfo;
import com.smart.util.StringUtils;
import com.thoughtworks.xstream.XStream;


/**
 * <b>Description:应用工具</b><br>
 * @author jeason
 */
public class ApplicationUtils {

	private static Map<String, Application> applications=null;
	
	public static Application getById(String id) {
		
		if(StringUtils.isBlank(id)) return null;
		
		if(applications==null){
			init();
		}
		
		return applications==null?null:applications.get(id);
	}
	
	public static void init() {
		
		synchronized (ApplicationUtils.class) {
			if(applications==null){
				XStream xs=new XStream();
				xs.alias("Application", Application.class);
				xs.alias("gipcMasShotApplicationAuthInfo", GipcMasShotApplicationAuthInfo.class);
				xs.alias("ucpaasShotMessageApplicationAuthInfo", UcpaasShotMessageApplicationAuthInfo.class);
				
				@SuppressWarnings("unchecked")
				List<Application> appList=(List<Application>) xs.fromXML(ApplicationUtils.class.getResourceAsStream("/application.xml"));
				if(!CollectionUtils.isEmpty(appList)){
					applications=new HashMap<String, Application>(appList.size());
					for(Application app:appList){
						applications.put(app.getId(), app);
					}
				}
			}
		}

	}

	public static Application[] getListByIds(String[] applicationIds) {
		
		if(applicationIds==null || applicationIds.length<=0){
			return null;
		}
		
		Application[] apps=new Application[applicationIds.length];
		for(int i=0,size=applicationIds.length;i<size;i++){
			
			String appId=applicationIds[i];
			Application app=getById(appId);
			apps[i]=app;
		}
		return apps;
	}
}
