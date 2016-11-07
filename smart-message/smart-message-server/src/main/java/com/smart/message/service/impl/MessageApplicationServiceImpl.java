package com.smart.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.message.dao.MessageApplicationDao;
import com.smart.message.model.MessageApplication;
import com.smart.message.service.MessageApplicationService;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;

@Service("messageApplicationService")
public class MessageApplicationServiceImpl extends ServiceImpl<MessageApplicationDao, MessageApplication, Integer>
	implements MessageApplicationService {
	
	@Autowired
	public void setDao(MessageApplicationDao dao) {
		this.dao = dao;
	}
}
