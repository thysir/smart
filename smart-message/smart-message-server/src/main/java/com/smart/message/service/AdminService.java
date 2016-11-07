package com.smart.message.service;

import com.smart.message.dao.AdminDao;
import com.smart.message.model.Admin;
import com.smart.mvc.service.mybatis.Service;

/**
 * 管理员服务接口
 * 
 * @author Joe
 */
public interface AdminService extends Service<AdminDao, Admin, Integer> {
	
	/**
	 * Description:通过账号获取用户
	 * @author jeason
	 * @param account 账号
	 * @return
	 */
	Admin findByAccount(String account);
	
	/**
	 * Description:更新密码
	 * @author 唐海洋
	 * @Version 1.0 2016-9-8下午10:21:09 
	 * @param id 管理员id
	 * @param newPwd 新密码
	 */
	void updatePwd(Integer id, String newPwd);
}