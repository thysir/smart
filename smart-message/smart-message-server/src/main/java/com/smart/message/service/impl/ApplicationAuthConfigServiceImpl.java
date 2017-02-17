package com.smart.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.smart.message.dao.ApplicationAuthConfigDao;
import com.smart.message.model.ApplicationAuthConfig;
import com.smart.message.service.ApplicationAuthConfigService;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.util.StringUtils;

@Service("applicationAuthConfigService")
public class ApplicationAuthConfigServiceImpl extends ServiceImpl<ApplicationAuthConfigDao, ApplicationAuthConfig, Integer>
	implements ApplicationAuthConfigService {
	
	@Autowired
	public void setDao(ApplicationAuthConfigDao dao) {
		this.dao = dao;
	}

	@Override
	public Pagination<ApplicationAuthConfig> findPagination(Pagination<ApplicationAuthConfig> pagination,Integer applicationTypeId) {
		
		dao.findPagination(pagination,applicationTypeId);
		return pagination;
	}

	@Override
	public ApplicationAuthConfig findByApplicationTypeIdAndAuthKey(
			Integer applicationTypeId, String authKey) {
		
		if(applicationTypeId==null || StringUtils.isBlank(authKey)) return null;
		
		return dao.findByApplicationTypeIdAndAuthKey(applicationTypeId, authKey);
	}

	@Override
	public List<ApplicationAuthConfig> findByApplicationTypeId(
			Integer applicationTypeId) {
		
		if(applicationTypeId==null) return null;
		
		return dao.getListByApplicationTypeId(applicationTypeId);
	}

	@Override
	public void deletByApplicationTypeIds(List<Integer> applicationTypeIdList) {

		if(CollectionUtils.isEmpty(applicationTypeIdList)) return;
		
		dao.deletByApplicationTypeIds(applicationTypeIdList);
	}
}
