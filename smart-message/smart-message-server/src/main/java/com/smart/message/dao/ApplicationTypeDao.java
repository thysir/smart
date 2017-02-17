package com.smart.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.message.model.ApplicationType;
import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;

public interface ApplicationTypeDao extends Dao<ApplicationType, Integer> {

	List<ApplicationType> findPagination(Pagination<ApplicationType> p, @Param("name") String name);

	/**
	 * Description:获取所有记录
	 * @author jeason
	 * @return
	 */
	List<ApplicationType> getAll();

	/**
	 * Description:通过类型获取
	 * @author jeason
	 * @param type
	 * @return
	 */
	ApplicationType getByType(@Param("type") Integer type);
}
