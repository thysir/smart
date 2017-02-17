package com.smart.message.service;

import com.smart.message.dao.UserApplicationDao;
import com.smart.message.model.UserApplication;
import com.smart.mvc.service.mybatis.Service;

/**
 * <b>Description:用户与应用映射接口</b><br>
 * @author jeason
 */
public interface UserApplicationService extends Service<UserApplicationDao, UserApplication, Integer> {

	/**
	 * Description:通过用户id删除
	 * @author jeason
	 * @param userId 用户id
	 * @return
	 */
	void deleteByUserId(Integer userId);
	
	/**
	 * Description:通过应用id删除
	 * @author jeason
	 * @param applicationId 应用id
	 * @return
	 */
	void deleteByApplicationId(Integer applicationId);
}