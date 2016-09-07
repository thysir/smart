package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.mvc.provider.PasswordProvider;
import com.smart.sso.rpc.AuthenticationRpcService;
import com.smart.sso.rpc.Menu;
import com.smart.sso.rpc.RpcUser;
import com.smart.sso.server.common.LoginUser;
import com.smart.sso.server.common.TokenManager;
import com.smart.sso.server.model.App;
import com.smart.sso.server.model.User;
import com.smart.sso.server.model.UserApp;
import com.smart.sso.server.service.AppService;
import com.smart.sso.server.service.PermissionService;
import com.smart.sso.server.service.UserAppService;
import com.smart.sso.server.service.UserService;
import com.smart.util.StringUtils;
import com.smart.util.ValidateUtils;

@Service("authenticationRpcService")
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {

	@Resource
	private PermissionService permissionService;
	@Resource
	private UserService userService;
	@Resource
	private AppService appService;
	@Resource
	private UserAppService userAppService;

	@Override
	public boolean validate(String token) {
		return TokenManager.validate(token) != null;
	}
	
	@Override
	public RpcUser findAuthInfo(String token, String appCode) {
		LoginUser user = TokenManager.validate(token);
		if (user != null) {
			return new RpcUser(user.getUserName(), user.getProfile());
		}
		return null;
	}
	
	@Override
	public List<Menu> findPermissionList(String token, String appCode) {
		if (StringUtils.isBlank(token)) {
			return permissionService.findListById(appCode, null);
		}
		else {
			LoginUser user = TokenManager.validate(token);
			if (user != null) {
				return permissionService.findListById(appCode, user.getUserId());
			}
			else {
				return new ArrayList<Menu>(0);
			}
		}
	}
	
	@Override
	public boolean updatePassword(String token, String newPassword) {
		LoginUser loginUser = TokenManager.validate(token);
		if (loginUser != null) {
			User user = userService.get(loginUser.getUserId());
			user.setPassword(PasswordProvider.encrypt(newPassword));
			int rows = userService.update(user);
			if (rows == 1)
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean regiestUser(String token, String appCode, String account,String password) {
		
		if(!validate(token)){
			//权限验证失败
			return false;
		}
		
		if(StringUtils.isBlank(appCode) || StringUtils.isBlank(account) || StringUtils.isBlank(password)){
			//参数验证失败
			return false;
		}
		
		//验证账号信息
		if(!ValidateUtils.isUsername(account)){
			//只能包含英文字母、数字、\"-\"和\"_\"
			return false;
		}
		User user=userService.findByAccount(account);
		if(user!=null){
			//账号已经存在,不能重复注册
			return false;
		}
		
		//验证appCode是否存在
		App app=appService.findByCode(appCode);
		if(app==null){
			//应用不存在
			return false;
		}
		
		//数据验证通过，开始执行保存
		user=new User();
		user.setAccount(account);
		user.setCreateTime(new Date());
		user.setIsEnable(true);
		user.setLoginCount(0);
		user.setPassword(PasswordProvider.encrypt(password));
		userService.save(user);
		
		UserApp userApp=new UserApp();
		userApp.setAppId(app.getId());
		userApp.setUserId(user.getId());
		userAppService.save(userApp);
		
		return true;
	}
}
