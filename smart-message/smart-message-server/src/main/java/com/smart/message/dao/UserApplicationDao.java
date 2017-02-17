package com.smart.message.dao;

import org.apache.ibatis.annotations.Param;

import com.smart.message.model.UserApplication;
import com.smart.mvc.dao.mybatis.Dao;

/**
 * <b>Description:用户与应用映射接口</b><br>
 * @author jeason
 */
public interface UserApplicationDao extends Dao<UserApplication, Integer> {

	/**
	 * Description:通过用户id删除
	 * @author jeason
	 * @param userId 用户id
	 * @return
	 */
	int deleteByUserId(@Param("userId") Integer userId);
	
	/**
	 * Description:通过应用id删除
	 * @author jeason
	 * @param applicationId 应用id
	 * @return
	 */
	int deleteByApplicationId(@Param("applicationId") Integer applicationId);
}
