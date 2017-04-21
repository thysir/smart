package com.smart.message.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.message.model.ApplicationCallLog;
import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;

public interface ApplicationCallLogDao extends Dao<ApplicationCallLog, Integer> {

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
	List<ApplicationCallLog> findPagination(Pagination<ApplicationCallLog> p,
			@Param("applicationId") Integer applicationId, @Param("receiver") String receiver, @Param("content") String content,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	/**
	 * Description:通过应用id删除授权信息
	 * @author jeason
	 * @param applicationIdList 应用id列表
	 */
	void deleteByApplicationIds(List<Integer> idList);

}