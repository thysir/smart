package com.smart.message.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.smart.message.dao.UserDao;
import com.smart.message.model.User;
import com.smart.message.model.UserApplication;
import com.smart.message.service.UserApplicationService;
import com.smart.message.service.UserService;
import com.smart.message.vo.UserListItem;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.util.StringUtils;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User, Integer> implements UserService {
	
	@Resource private UserApplicationService userApplicationService;
	
	@Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Transactional
	@Override
	public void save(User bean, List<Integer> applicationIds) {
		
		if(bean==null) return;
		
		saveOrUpdate(bean);
		
		//更新映射数据
		userApplicationService.deleteByUserId(bean.getId());
		if(!CollectionUtils.isEmpty(applicationIds)){
			List<UserApplication> userApplicationList=new ArrayList<UserApplication>(applicationIds.size());
			for(Integer applicationId:applicationIds){
				UserApplication userApp=new UserApplication();
				userApp.setUserId(bean.getId());
				userApp.setApplicationId(applicationId);
				userApplicationList.add(userApp);
			}
			userApplicationService.save(userApplicationList);
		}
	}

	@Override
	public User getByAccount(String account) {
		
		if(StringUtils.isBlank(account)) return null;
		
		return dao.getByAccount(account);
	}

	@Override
	public Pagination<UserListItem> findPagination(
			Pagination<UserListItem> pagination, String name) {
		
		return dao.findPagination(pagination, name);
	}
}