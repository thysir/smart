package com.smart.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.smart.message.ApplicationAuthInfo;
import com.smart.message.dao.ApplicationAuthDao;
import com.smart.message.model.ApplicationAuth;
import com.smart.message.service.ApplicationAuthService;
import com.smart.message.vo.ApplicationAuthItem;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;

@Service("applicationAuthService")
public class ApplicationAuthServiceImpl extends ServiceImpl<ApplicationAuthDao, ApplicationAuth, Integer>
	implements ApplicationAuthService {
	
	@Autowired
	public void setDao(ApplicationAuthDao dao) {
		this.dao = dao;
	}

	@Override
	public Pagination<ApplicationAuth> findPagination(Pagination<ApplicationAuth> pagination,Integer applicationId) {
		
		dao.findPagination(pagination,applicationId);
		return pagination;
	}

	@Override
	public ApplicationAuthInfo getApplicationAuthInfo(Integer applicationId) {
		
		if(applicationId==null) return null;
		
		List<ApplicationAuth> applicationAuthList=dao.getListByApplicationId(applicationId);
		
		return new ApplicationAuthInfo(applicationAuthList);
	}

	@Override
	public List<ApplicationAuthItem> findAuthInfoByApplicationIdAndApplicationTypeId(
			Integer applicationId, Integer applicationTypeId) {
		
		if(applicationTypeId==null) return null;
		
		return dao.findAuthInfoByApplicationIdAndApplicationTypeId(applicationId, applicationTypeId);
	}

	@Override
	public void deleteByApplicationId(Integer applicationId) {
		
		if(applicationId==null) return;
		
		dao.deleteByApplicationId(applicationId);
	}

	@Override
	public void deleteByApplicationIds(List<Integer> applicationIdList) {
		
		if(CollectionUtils.isEmpty(applicationIdList)) return;
		
		dao.deleteByApplicationIds(applicationIdList);
	}
}
