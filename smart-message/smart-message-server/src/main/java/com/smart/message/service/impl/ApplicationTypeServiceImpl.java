package com.smart.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.message.dao.ApplicationTypeDao;
import com.smart.message.model.ApplicationType;
import com.smart.message.service.ApplicationAuthConfigService;
import com.smart.message.service.ApplicationTypeService;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;

@Service("applicationTypeService")
public class ApplicationTypeServiceImpl extends ServiceImpl<ApplicationTypeDao, ApplicationType, Integer>
	implements ApplicationTypeService {
	
	@Resource private ApplicationAuthConfigService applicationAuthConfigService;
	
	@Autowired
	public void setDao(ApplicationTypeDao dao) {
		this.dao = dao;
	}

	@Override
	public Pagination<ApplicationType> findPagination(Pagination<ApplicationType> pagination, String name) {
		
		dao.findPagination(pagination, name);
		return pagination;
	}

	@Override
	public List<ApplicationType> getAll() {
		return dao.getAll();
	}

	@Override
	public ApplicationType getByType(Integer type) {
		
		if(type==null) return null;
		
		return dao.getByType(type);
	}
	
	@Transactional
	@Override
	public int deleteById(List<Integer> idList) {
		
		applicationAuthConfigService.deletByApplicationTypeIds(idList);
		
		return super.deleteById(idList);
	}
}