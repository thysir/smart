package com.smart.sso.server.service;

import java.util.List;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;
import com.smart.sso.server.dao.ModularDao;
import com.smart.sso.server.model.Modular;

public interface ModularService extends Service<ModularDao, Modular, Integer> {

	List<Modular> findByAppId(Integer appId);
	
	Pagination<Modular> findPaginationByAppId(Integer appId, Integer pageNo, Integer pageSize);
	
}