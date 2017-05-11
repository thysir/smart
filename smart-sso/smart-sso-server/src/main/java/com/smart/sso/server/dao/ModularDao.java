package com.smart.sso.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;
import com.smart.sso.server.model.Modular;

/**
 * 持久化接口
 * 
 * @author Joe
 */
public interface ModularDao extends Dao<Modular, Integer> {
	
	public List<Modular> findByAppId(@Param("appId") Integer appId);

	public List<Modular> findPaginationByAppId(Pagination<Modular> p, @Param("appId") Integer appId);
}
