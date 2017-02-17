package com.smart.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.message.model.Application;
import com.smart.message.vo.ApplicationListItem;
import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;

public interface ApplicationDao extends Dao<Application, Integer> {

	List<ApplicationListItem> findPagination(Pagination<ApplicationListItem> p, @Param("name") String name);

	/**
	 * Description:保存访问记录
	 * @author jeason
	 * @param id 应用id
	 * @param isSuccess 是否发送成功
	 */
	void saveAccessRecord(@Param("id") Integer id, @Param("isSuccess") boolean isSuccess);
}
