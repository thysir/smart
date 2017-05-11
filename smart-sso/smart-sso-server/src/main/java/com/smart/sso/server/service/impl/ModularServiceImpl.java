package com.smart.sso.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.server.dao.ModularDao;
import com.smart.sso.server.model.Modular;
import com.smart.sso.server.model.Permission;
import com.smart.sso.server.service.ModularService;
import com.smart.sso.server.service.PermissionService;

@Service("modularService")
public class ModularServiceImpl extends ServiceImpl<ModularDao, Modular, Integer> implements ModularService {

	@Resource private PermissionService permissionService;
	
	@Autowired
	@Override
	public void setDao(ModularDao dao) {
		this.dao = dao;
	}

	@Override
	public Pagination<Modular> findPaginationByAppId(Integer appId,
			Integer pageNo, Integer pageSize) {
		
		if(appId==null) return null;
		
		Pagination<Modular> p=new Pagination<Modular>(pageNo, pageSize);
		dao.findPaginationByAppId(p,appId);
		return p;
	}
	
	@Override
	public int deleteById(Integer id) {
		//删除底下的权限
		List<Permission> permissionList=permissionService.findByModularId(id);
		if(!CollectionUtils.isEmpty(permissionList)){
			for(Permission permission:permissionList){
				permissionService.deletePermission(permission.getId(), permission.getAppId());
			}
		}
		
		return super.deleteById(id);
	}

	@Override
	public List<Modular> findByAppId(Integer appId) {
		
		if(appId==null) return null;
		return dao.findByAppId(appId);
	}

}
