package com.smart.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.message.model.ApplicationAuth;
import com.smart.message.vo.ApplicationAuthItem;
import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;

public interface ApplicationAuthDao extends Dao<ApplicationAuth, Integer> {

	List<ApplicationAuth> findPagination(Pagination<ApplicationAuth> p, @Param("applicationId") Integer applicationId);

	/**
	 * Description:通过应用id获取授权信息
	 * @author jeason
	 * @param applicationId
	 * @return
	 */
	List<ApplicationAuth> getListByApplicationId(Integer applicationId);

	/**
	 * Description:通过appid与类型id获取授权信息
	 * @author jeason
	 * @param applicationId 应用id
	 * @param applicationTypeId 类型id
	 * @return
	 */
	List<ApplicationAuthItem> findAuthInfoByApplicationIdAndApplicationTypeId(
			@Param("applicationId") Integer applicationId, @Param("applicationTypeId") Integer applicationTypeId);

	/**
	 * Description:删除应用下所有的授权信息
	 * @author jeason
	 * @param applicationId
	 */
	void deleteByApplicationId(@Param("applicationId") Integer applicationId);

	/**
	 * Description:通过应用id删除授权信息
	 * @author jeason
	 * @param applicationIdList 应用id列表
	 */
	void deleteByApplicationIds(List<Integer> applicationIdList);
}
