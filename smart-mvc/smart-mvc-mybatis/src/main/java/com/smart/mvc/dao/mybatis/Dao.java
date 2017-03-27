package com.smart.mvc.dao.mybatis;

import java.util.Collection;
import java.util.List;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.mybatis.QueryPropertys;

/**
 * Dao接口
 * 
 * @author Joe
 */
public interface Dao<T, ID> {

	/**
	 * 如果实体未持久化过则新建实体，否则更新实体
	 * 
	 * @param T
	 *            t
	 */
	public int save(T t);

	/**
	 * 更新实体
	 * 
	 * @param T
	 *            t
	 */
	public int update(T t);

	/**
	 * 删除实体
	 * 
	 * @param T
	 *            t
	 */
	public int deleteById(ID id);
	
	/**
	 * 删除实体
	 * 
	 * @param T
	 *            t
	 */
	public int deleteById(Collection<ID> idList);

	/**
	 * 通过主键加载实体
	 * 
	 * @param PK
	 *            pk
	 * @return T
	 */
	public T get(ID pk);
	
	/**
	 * 查所有分页
	 * 
	 * @param p
	 * @return
	 */
	public List<T> findByAll(Pagination<T> p);
	
	/**
	 * 通过属性进行查找
	 * @param queryPropertys
	 * @return
	 */
	public List<T> findListByPropertys(QueryPropertys queryPropertys);
}
