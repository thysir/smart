package com.smart.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.smart.message.ApplicationInfo;
import com.smart.message.dao.ApplicationDao;
import com.smart.message.model.Application;
import com.smart.message.model.ApplicationAuth;
import com.smart.message.model.ApplicationType;
import com.smart.message.service.ApplicationAuthService;
import com.smart.message.service.ApplicationService;
import com.smart.message.service.ApplicationTypeService;
import com.smart.message.vo.ApplicationListItem;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;

@Service("applicationService")
public class ApplicationServiceImpl extends ServiceImpl<ApplicationDao, Application, Integer>
	implements ApplicationService {
	
	@Resource private ApplicationTypeService applicationTypeService;
	@Resource private ApplicationAuthService applicationAuthService;
	
	@Autowired
	public void setDao(ApplicationDao dao) {
		this.dao = dao;
	}

	@Override
	public Pagination<ApplicationListItem> findPagination(Pagination<ApplicationListItem> p, String name) {
		
		dao.findPagination(p, name);
		return p;
	}

	@Override
	public void saveOrUpdate(Application bean, List<ApplicationAuth> authList) {
		
		saveOrUpdate(bean);
		
		//删除授权信息信息
		applicationAuthService.deleteByApplicationId(bean.getId());
		
		//添加新的授权信息
		if(!CollectionUtils.isEmpty(authList)){
			for(ApplicationAuth item:authList){
				
				ApplicationAuth authBean=new ApplicationAuth();
				authBean.setApplicationId(bean.getId());
				authBean.setAuthKey(item.getAuthKey());
				authBean.setAuthValue(item.getAuthValue());
				
				applicationAuthService.save(authBean);
			}
		}
	}

	@Override
	public ApplicationInfo getByApplicationId(Integer applicationId) {
		
		if(applicationId==null) return null;
		
		ApplicationInfo applicationInfo=null;
		Application app=get(applicationId);
		if(app!=null){
			
			ApplicationType applicationType=applicationTypeService.get(app.getApplicationTypeId());
			
			applicationInfo=new ApplicationInfo();
			applicationInfo.setId(app.getId());
			applicationInfo.setType(applicationType.getType());
			applicationInfo.setApplicationAuthInfo(applicationAuthService.getApplicationAuthInfo(applicationId));
		}
		
		return applicationInfo;
	}
	
	@Transactional
	@Override
	public void deleteById(List<Integer> idList) {
		
		applicationAuthService.deleteByApplicationIds(idList);
		
		super.deleteById(idList);
	}

	@Override
	public void saveAccessRecord(Integer id, boolean isSuccess) {
		
		dao.saveAccessRecord(id,isSuccess);
	}
}
