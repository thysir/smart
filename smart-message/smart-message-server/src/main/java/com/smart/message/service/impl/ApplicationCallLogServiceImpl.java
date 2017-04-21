package com.smart.message.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.smart.message.dao.ApplicationCallLogDao;
import com.smart.message.model.ApplicationCallLog;
import com.smart.message.service.ApplicationCallLogService;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;

@Service("applicationCallLogService")
public class ApplicationCallLogServiceImpl extends ServiceImpl<ApplicationCallLogDao, ApplicationCallLog, Integer>
	implements ApplicationCallLogService {
	
	@Autowired
	public void setDao(ApplicationCallLogDao dao) {
		this.dao = dao;
	}

	@Override
	public Pagination<ApplicationCallLog> findPagination(
			Pagination<ApplicationCallLog> p, Integer applicationId,
			String receiver, String content, Date beginTime, Date endTime) {
		
		if(p==null) return null;
		
		dao.findPagination(p, applicationId, receiver, content, beginTime, endTime);
		return p;
	}

	@Override
	public void deleteByApplicationIds(List<Integer> idList) {
		
		if(CollectionUtils.isEmpty(idList)){
			return;
		}
		dao.deleteByApplicationIds(idList);
	}
}
