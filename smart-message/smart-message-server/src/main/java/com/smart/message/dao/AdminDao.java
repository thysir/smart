package com.smart.message.dao;

import org.apache.ibatis.annotations.Param;

import com.smart.message.model.Admin;
import com.smart.mvc.dao.mybatis.Dao;

/**
 * 管理员持久化接口
 * 
 * @author Joe
 */
public interface AdminDao extends Dao<Admin, Integer> {
	
	/**
	 * Description:通过账号获取用户
	 * @author jeason
	 * @param account 账号
	 * @return
	 */
	Admin findByAccount(@Param("account") String account);
}
