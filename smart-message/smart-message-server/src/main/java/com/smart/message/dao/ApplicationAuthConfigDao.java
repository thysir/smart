package com.smart.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.message.model.ApplicationAuthConfig;
import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;

public interface ApplicationAuthConfigDao extends Dao<ApplicationAuthConfig, Integer> {

	/**
	 * Description:分页
	 * @author jeason
	 * @param p
	 * @param applicationTypeId
	 * @return
	 */
	List<ApplicationAuthConfig> findPagination(Pagination<ApplicationAuthConfig> p, @Param("applicationTypeId") Integer applicationTypeId);

	/**
	 * Description:通过应用类型获取授权信息
	 * @author jeason
	 * @param applicationTypeId
	 * @return
	 */
	List<ApplicationAuthConfig> getListByApplicationTypeId(Integer applicationTypeId);

	/**
	 * Description:获取对象
	 * @author jeason
	 * @param applicationTypeId
	 * @param authKey
	 * @return
	 */
	ApplicationAuthConfig findByApplicationTypeIdAndAuthKey(
			@Param("applicationTypeId") Integer applicationTypeId, @Param("authKey") String authKey);

	/**
	 * Description:通过应用类型id删除授权配置信息
	 * @author jeason
	 * @param applicationTypeIdList
	 */
	void deletByApplicationTypeIds(List<Integer> applicationTypeIdList);

}
