package com.smart.mvc.service.mybatis;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.smart.mvc.dao.mybatis.model.QueryPropertys;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.PersistentObject;

/**
 * Service接口
 * 
 * @param <DAO>
 * @param <T>
 * @param <ID>
 * @author Joe
 */
public interface Service<DAO, T extends PersistentObject, ID extends Serializable> {
	/**
	 * 设置数据访问对象
	 * 
	 * @param DAO
	 *            dao
	 */
	public void setDao(DAO dao);

	/**
	 * 新建实体
	 * 
	 * @param T
	 *            t
	 */
	public int save(T t);

	/**
	 * 新建实体
	 * 
	 * @param List
	 *            <T> ts
	 */
	public int save(Collection<T> ts);

	/**
	 * 更新实体
	 * 
	 * @param T
	 *            t
	 */
	public int update(T t);

	/**
	 * 更新实体
	 * 
	 * @param List
	 *            <T> ts
	 */
	public int update(Collection<T> ts);
	
	/**
	 * 保存或更新实体
	 * 
	 * @param List
	 *            <T> ts
	 */
	public int saveOrUpdate(T t);

	/**
	 * 删除实体
	 * 
	 * @param T
	 *            t
	 */
	public int delete(T t);

	/**
	 * 删除实体
	 * 
	 * @param List
	 *            <T> ts
	 */
	public int delete(Collection<T> ts);

	/**
	 * 通过主键加载实体
	 * 
	 * @param PK
	 *            pk
	 * @return T
	 */
	public T get(ID pk);

	/**
	 * 通过主键加载实体
	 * 
	 * @param List
	 *            <PK> pks
	 * @return List<T>
	 */
	public List<T> get(Collection<ID> pks);

	/**
	 * 通过主键删除实体
	 * 
	 * @param PK
	 *            pk
	 * @return T
	 */
	public int deleteById(ID id);

	/**
	 * 通过主键删除实体
	 * 
	 * @param List
	 *            <PK> pks
	 * @return List<T>
	 */
	public int deleteById(List<ID> idList);

	/**
	 * 查所有分页
	 * 
	 * @param p
	 * @return
	 */
	public Pagination<T> findByAllPagination(Pagination<T> p);
	
	/**
	 * 通过属性进行查找
	 * @param queryPropertys
	 * @return
	 */
	public T findByPropertys(QueryPropertys queryPropertys);
	
	/**
	 * 通过属性进行查找
	 * @param queryPropertys
	 * @return
	 */
	public List<T> findListByPropertys(QueryPropertys queryPropertys);
}
