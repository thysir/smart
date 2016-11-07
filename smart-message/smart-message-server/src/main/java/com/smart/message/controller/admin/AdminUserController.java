package com.smart.message.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.message.bean.sys.SessionAdmin;
import com.smart.message.model.Admin;
import com.smart.message.service.AdminService;
import com.smart.mvc.model.Result;
import com.smart.mvc.provider.PasswordProvider;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;

/**
 * <b>Description:管理员个人信息</b><br>
 * @author 唐海洋
 * @version 1.0 2016-9-2 上午10:58:49
 */
@Controller
@RequestMapping("/admin/admin")
public class AdminUserController extends BaseAdminController{

	@Resource private AdminService adminService;
	
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public String execute(HttpSession httpSession,Model model) {
		
		Integer adminId = getSessionAdmin(httpSession).getId();
		if(adminId!=null){
			Admin admin = adminService.get(adminId);
			model.addAttribute("bean", admin);
		}		
		return "/admin/info";
	}
	
	@RequestMapping(value = "/updatePassword",method = RequestMethod.GET)
	public String updatePassword() {
		
		return "/admin/updatePassword";
	}
	
	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	public @ResponseBody Result saveUserInfo(HttpSession httpSession,
			@ValidateParam(name = "昵称 ", validators=Validator.NOT_BLANK) String nikeName) {
		
		Integer adminId = getSessionAdminId(httpSession);
		if(adminId!=null){
			Admin admin = adminService.get(adminId);
			admin.setNikeName(nikeName);
			adminService.saveOrUpdate(admin);
			
			//更新ssession中相关信息
			SessionAdmin sessionAdmin=getSessionAdmin(httpSession);
			sessionAdmin.setName(nikeName);
			httpSession.setAttribute("session_admin",sessionAdmin);
		}

		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/savePassword",method = RequestMethod.POST)
	public @ResponseBody Result savePassword(HttpSession httpSession,
			@ValidateParam(name = "原密码 ",validators=Validator.NOT_BLANK) String oldPassword,
			@ValidateParam(name = "新密码 ",validators=Validator.NOT_BLANK) String newPassword) {
		
		Integer adminId = getSessionAdminId(httpSession);
		if(adminId!=null){
			Admin admin = adminService.get(adminId);
			String oldEncryptPassword=PasswordProvider.encrypt(oldPassword);
			if(!oldEncryptPassword.equals(admin.getPassword())){
				return Result.createErrorResult().setMessage("密码错误");
			}else{
				adminService.updatePwd(adminId,newPassword);
			}
		}
		
		return Result.createSuccessResult();
	}
	
}