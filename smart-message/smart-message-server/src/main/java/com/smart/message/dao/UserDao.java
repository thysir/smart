package com.smart.message.dao;

import org.apache.ibatis.annotations.Param;

import com.smart.message.model.User;
import com.smart.message.vo.UserListItem;
import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;

/**
 * <b>Description:用户接口</b><br>
 * @author jeason
 */
public interface UserDao extends Dao<User, Integer> {

	/**
	 * Description:通过账号获取数据
	 * @author jeason
	 * @param account
	 * @return
	 */
	User getByAccount(@Param("account") String account);

	/**
	 * Description:分页
	 * @author jeason
	 * @param pagination
	 * @param name 搜索关键字
	 * @return
	 */
	Pagination<UserListItem> findPagination(
			Pagination<UserListItem> pagination,@Param("name") String name);
	
}
