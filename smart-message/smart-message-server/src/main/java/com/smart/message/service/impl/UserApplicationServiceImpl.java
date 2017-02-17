package com.smart.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.message.dao.UserApplicationDao;
import com.smart.message.model.UserApplication;
import com.smart.message.service.UserApplicationService;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;

@Service("userApplicationService")
public class UserApplicationServiceImpl extends ServiceImpl<UserApplicationDao, UserApplication, Integer> implements UserApplicationService {
	
	@Autowired
	public void setDao(UserApplicationDao dao) {
		this.dao = dao;
	}

	@Override
	public void deleteByUserId(Integer userId) {

		if(userId==null) return;
		dao.deleteByUserId(userId);
	}

	@Override
	public void deleteByApplicationId(Integer applicationId) {

		if(applicationId==null) return;
		dao.deleteByApplicationId(applicationId);		
	}
}
