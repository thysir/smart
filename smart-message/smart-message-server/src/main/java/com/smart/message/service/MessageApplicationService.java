package com.smart.message.service;

import com.smart.message.dao.MessageApplicationDao;
import com.smart.message.model.MessageApplication;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;

public interface MessageApplicationService extends Service<MessageApplicationDao, MessageApplication, Integer> {

	/**
	 * Description:分页功能
	 * @author jeason
	 * @param pageNo 分页参数
	 * @param pageSize 分页参数
	 * @param name 搜索关键词：应用名称
	 * @return
	 */
	Pagination<MessageApplication> findPagination(Integer pageNo,
			Integer pageSize, String name);
	
}