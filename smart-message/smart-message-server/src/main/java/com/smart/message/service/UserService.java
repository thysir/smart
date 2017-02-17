package com.smart.message.service;

import java.util.List;

import com.smart.message.dao.UserDao;
import com.smart.message.model.User;
import com.smart.message.vo.UserListItem;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;

/**
 * <b>Description:用户接口</b><br>
 * @author jeason
 */
public interface UserService extends Service<UserDao, User, Integer> {

	/**
	 * Description:保存应用
	 * @author jeason
	 * @param bean
	 * @param applicationIds
	 */
	void save(User bean, List<Integer> applicationIds);

	/**
	 * Description:通过账号获取数据
	 * @author jeason
	 * @param account
	 * @return
	 */
	User getByAccount(String account);

	/**
	 * Description:分页
	 * @author jeason
	 * @param pagination
	 * @param name 搜索关键字
	 * @return
	 */
	Pagination<UserListItem> findPagination(
			Pagination<UserListItem> pagination, String name);
	
}