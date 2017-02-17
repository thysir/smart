package com.smart.message.service;

import java.util.List;

import com.smart.message.dao.ApplicationTypeDao;
import com.smart.message.model.ApplicationType;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;

public interface ApplicationTypeService extends Service<ApplicationTypeDao, ApplicationType, Integer> {

	/**
	 * Description:分页列表
	 * @author jeason
	 * @param pagination
	 * @param name 搜索关键词：名称
	 * @return
	 */
	Pagination<ApplicationType> findPagination(Pagination<ApplicationType> pagination, String name);

	/**
	 * Description:获取所有记录
	 * @author jeason
	 * @return
	 */
	List<ApplicationType> getAll();

	/**
	 * Description:通过应用类型获取
	 * @author jeason
	 * @param type
	 * @return
	 */
	ApplicationType getByType(Integer type);

}