package com.smart.message.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.smart.message.ApplicationInfo;
import com.smart.message.dao.ApplicationDao;
import com.smart.message.model.Application;
import com.smart.message.model.ApplicationAuth;
import com.smart.message.vo.ApplicationListItem;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;

public interface ApplicationService extends Service<ApplicationDao, Application, Integer> {

	/**
	 * Description:分页列表
	 * @author jeason
	 * @param p
	 * @param name 搜索关键词：应用名称
	 * @return
	 */
	Pagination<ApplicationListItem> findPagination(Pagination<ApplicationListItem> p, String name);

	/**
	 * Description:保存
	 * @author jeason
	 * @param bean
	 * @param authList 授权信息
	 */
	@Transactional
	void saveOrUpdate(Application bean, List<ApplicationAuth> authList);

	/**
	 * Description:获取应用信息
	 * @author jeason
	 * @param applicationId
	 * @return
	 */
	ApplicationInfo getByApplicationId(Integer applicationId);

	/**
	 * Description:保存访问记录
	 * @author jeason
	 * @param id 应用id
	 * @param isSuccess 是否发送成功
	 */
	void saveAccessRecord(Integer id, boolean isSuccess);
}