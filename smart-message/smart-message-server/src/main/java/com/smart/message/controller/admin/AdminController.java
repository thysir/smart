package com.smart.message.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smart.mvc.model.Result;
import com.smart.sso.rpc.Permissionable;

/**
 * <b>Description:首页</b><br>
 * @author 唐海洋
 * @version 1.0 2016-9-2 上午10:58:49
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseAdminController{

	@RequestMapping(value = "index",method = RequestMethod.GET)
	public ModelAndView execute(HttpSession httpSession) {
		
		Map<String, Object> model=new HashMap<String, Object>(2);
		model.put("sessionAdmin", getSessionAdmin(httpSession));
		
		return new ModelAndView("/admin/index",model);
	}

	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public @ResponseBody Result menu(HttpServletRequest request) {
		Object list = request.getSession().getAttribute(Permissionable.SESSION_USER_MENU);
		// 如果配置的权限拦截器，则获取登录用户权限下的菜单，没有权限拦截限制的情况下，获取当前系统菜单呈现
		return Result.createSuccessResult().setData(list == null ? request.getSession().getAttribute(Permissionable.APPLICATION_MENU) : list);
	}
}