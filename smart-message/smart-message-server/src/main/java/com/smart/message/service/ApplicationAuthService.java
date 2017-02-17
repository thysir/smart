package com.smart.message.service;

import java.util.List;

import com.smart.message.ApplicationAuthInfo;
import com.smart.message.dao.ApplicationAuthDao;
import com.smart.message.model.ApplicationAuth;
import com.smart.message.vo.ApplicationAuthItem;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;

public interface ApplicationAuthService extends Service<ApplicationAuthDao, ApplicationAuth, Integer> {

	/**
	 * Description:分页列表
	 * @author jeason
	 * @param pagination
	 * @param applicationId 
	 * @return
	 */
	Pagination<ApplicationAuth> findPagination(Pagination<ApplicationAuth> pagination,
			Integer applicationId);
	
	/**
	 * Description:通过应用id获取应用授权信息
	 * @author jeason
	 * @param applicationId 应用id
	 * @return
	 */
	ApplicationAuthInfo getApplicationAuthInfo(Integer applicationId);

	/**
	 * Description:通过appid与类型id获取授权信息
	 * @author jeason
	 * @param applicationId 应用id
	 * @param applicationTypeId 类型id
	 * @return
	 */
	List<ApplicationAuthItem> findAuthInfoByApplicationIdAndApplicationTypeId(
			Integer applicationId, Integer applicationTypeId);

	/**
	 * Description:删除应用下所有的授权信息
	 * @author jeason
	 * @param applicationId
	 */
	void deleteByApplicationId(Integer applicationId);

	/**
	 * Description:通过应用id删除授权信息
	 * @author jeason
	 * @param applicationIdList 应用id列表
	 */
	void deleteByApplicationIds(List<Integer> applicationIdList);
}