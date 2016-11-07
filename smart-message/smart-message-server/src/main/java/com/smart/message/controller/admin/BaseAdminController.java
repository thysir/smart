package com.smart.message.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.smart.message.bean.sys.SessionAdmin;
import com.smart.message.model.Admin;
import com.smart.message.service.AdminService;
import com.smart.mvc.controller.BaseController;
import com.smart.sso.rpc.Permissionable;
import com.smart.util.StringUtils;


public abstract class BaseAdminController extends BaseController{
	
	@Resource protected AdminService adminService;
	
	/**
	 * Description:获取session中保存的当前登录用户
	 * @author 唐海洋
	 * @Version 1.0 2015-9-19下午3:11:26 
	 * @return
	 */
	protected SessionAdmin getSessionAdmin(HttpSession httpSession) {
		
		SessionAdmin sessionAdmin=(SessionAdmin) httpSession.getAttribute("session_admin");
		if(sessionAdmin==null){
			String loginName=(String) httpSession.getAttribute(Permissionable.SESSION_ACCOUNT);
			Admin loginAdmin=adminService.findByAccount(loginName);
			if(loginAdmin!=null){
				String nikeName=StringUtils.isBlank(loginAdmin.getNikeName())?loginAdmin.getAccount():loginAdmin.getNikeName();
				sessionAdmin=new SessionAdmin(loginAdmin.getId(), nikeName);
				httpSession.setAttribute("session_admin",sessionAdmin);
			}
		}
		return sessionAdmin;
	}
	
	/**
	 * Description::获取session中保存的当前登录用户id
	 * @author 唐海洋
	 * @Version 1.0 2015-9-19下午3:12:02 
	 * @return
	 */
	protected Integer getSessionAdminId(HttpSession httpSession) {
		
		Integer sessionAdminId=null;
		SessionAdmin sessionAdmin=getSessionAdmin(httpSession);
		if(sessionAdmin!=null){
			sessionAdminId=getSessionAdmin(httpSession).getId();
		}
		return sessionAdminId;
	}
}
