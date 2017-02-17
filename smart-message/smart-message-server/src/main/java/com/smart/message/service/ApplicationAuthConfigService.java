package com.smart.message.service;

import java.util.List;

import com.smart.message.dao.ApplicationAuthConfigDao;
import com.smart.message.model.ApplicationAuthConfig;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;

public interface ApplicationAuthConfigService extends Service<ApplicationAuthConfigDao, ApplicationAuthConfig, Integer> {

	/**
	 * Description:分页列表
	 * @author jeason
	 * @param pagination
	 * @param applicationTypeId 
	 * @return
	 */
	Pagination<ApplicationAuthConfig> findPagination(Pagination<ApplicationAuthConfig> pagination,
			Integer applicationTypeId);

	/**
	 * Description:获取对象
	 * @author jeason
	 * @param applicationTypeId
	 * @param authKey
	 * @return
	 */
	ApplicationAuthConfig findByApplicationTypeIdAndAuthKey(
			Integer applicationTypeId, String authKey);

	/**
	 * Description:获取指定类型的授权配置信息
	 * @author jeason
	 * @param applicationTypeId 类型id
	 * @return
	 */
	List<ApplicationAuthConfig> findByApplicationTypeId(
			Integer applicationTypeId);

	/**
	 * Description:通过应用类型id删除授权配置信息
	 * @author jeason
	 * @param applicationTypeIdList
	 */
	void deletByApplicationTypeIds(List<Integer> applicationTypeIdList);
	
}