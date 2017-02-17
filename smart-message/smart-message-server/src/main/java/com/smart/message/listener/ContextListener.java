package com.smart.message.listener;

import javax.servlet.ServletContextEvent;

import com.smart.mvc.listener.InitListener;

public class ContextListener extends InitListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		super.contextInitialized(event);
		
//		ServletContext servletContext = (ServletContext) event.getServletContext();
		
//		//初始化枚举接口
//		ClasspathPackageScanner classScanner=new ClasspathPackageScanner("com.smart.message.enums");
//		List<String> enumClassNameList=classScanner.getFullyQualifiedClassNameList(EnumItemable.class);
//		servletContext.setAttribute(EnumItemable.class.getSimpleName(), enumClassNameList);
	}
}
