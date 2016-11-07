package com.smart.message.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.message.dao.AdminDao;
import com.smart.message.model.Admin;
import com.smart.message.service.AdminService;
import com.smart.mvc.provider.PasswordProvider;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.rpc.AuthenticationRpcService;
import com.smart.util.StringUtils;

@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin, Integer> implements AdminService {
	
	@Resource private AuthenticationRpcService authenticationRpcService;

	@Autowired
	public void setDao(AdminDao dao) {
		this.dao = dao;
	}

	@Override
	public Admin findByAccount(String account) {
		
		if(StringUtils.isBlank(account)) return null;
		
		return dao.findByAccount(account);
	}
	
	@Override
	public void updatePwd(Integer id, String newPwd) {
		
		if(id!=null && StringUtils.isNotBlank(newPwd)){
			
			Admin db=get(id);
			if(db!=null){
				boolean success=authenticationRpcService.updatePassword(SecurityUtils.getSubject().getPrincipal().toString(), newPwd);
				if(success){
					db.setPassword(PasswordProvider.encrypt(newPwd));
					saveOrUpdate(db);
				}
			}
		}
	}
}
