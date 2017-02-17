package com.smart.message.service;

import java.util.Date;

import com.smart.message.dao.ApplicationCallLogDao;
import com.smart.message.model.ApplicationCallLog;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;

public interface ApplicationCallLogService extends Service<ApplicationCallLogDao, ApplicationCallLog, Integer> {

	/**
	 * Description:分页操作
	 * @author jeason
	 * @param p
	 * @param applicationId 应用id
	 * @param receiver 查询关键字：消息接收人
	 * @param content 查询关键字：消息内容
	 * @param beginTime 查询开始时间
	 * @param endTime 查询结束时间
	 * @return
	 */
	Pagination<ApplicationCallLog> findPagination(Pagination<ApplicationCallLog> p, Integer applicationId, String receiver,
			String content,	Date beginTime, Date endTime);

}