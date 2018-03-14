package com.sun.szk.base.core.service;

import java.io.Serializable;
import java.util.List;

import com.sun.szk.base.core.controller.form.QueryAssistor;
import com.sun.szk.base.core.exception.ServiceException;
import com.sun.szk.base.core.result.QueryResult;

/**
 * 服务接口的基类
 *
 * @param <T>此服务接口服务的数据模型，即model
 */
public interface BaseService<T> {
	/**
	 * 保存实体
	 *
	 * @param entity
	 *            欲保存的实体
	 * @throws ServiceException
	 */
	void save(T entity) throws ServiceException;

	/**
	 * 根据选择的保存实体
	 *
	 * @param entity
	 *            欲保存的实体
	 * @throws ServiceException
	 */
	void saveForSelective(T entity) throws ServiceException;

	/**
	 * 更新实体
	 *
	 * @param entity
	 *            实体id
	 */
	void update(T entity) throws ServiceException;

	/**
	 * 根据选择的更新实体
	 *
	 * @param entity
	 * @throws ServiceException
	 */
	void updateForSelective(T entity) throws ServiceException;

	/**
	 * 删除实体
	 *
	 * @param id
	 * @throws ServiceException
	 */
	void delete(Serializable id) throws ServiceException;

	/**
	 * 根据条件删除
	 *
	 * @param entity
	 *            根据非空字段当做删除条件
	 * @throws ServiceException
	 */
	void deleteByCondition(T entity) throws ServiceException;

	/**
	 * 根据条件删除
	 *
	 * @param entity
	 *            根据非空字段当做删除条件
	 * @throws ServiceException
	 */
	public void deleteByCondition(T entity, QueryAssistor queryAssistor) throws ServiceException;

	/**
	 * 获取实体
	 *
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	T find(Serializable id) throws ServiceException;

	/**
	 * 获取数据
	 *
	 * @param entity
	 * @param queryAssistor
	 *            {@link QueryAssistor}
	 * @return 根据查询条件查询的查询结果集
	 * @throws ServiceException
	 */
	List<T> pageAll(T entity, QueryAssistor queryAssistor) throws ServiceException;

	/**
	 * 获取数据
	 *
	 * @param entity
	 * @param queryAssistor
	 *            {@link QueryAssistor}
	 * @return 根据查询条件查询的查询结果集
	 */
	public QueryResult<T> getData(T entity, QueryAssistor queryAssistor) throws ServiceException;

	/**
	 * 按条件查询记录总数
	 *
	 * @param form
	 * @return
	 * @throws ServiceException
	 */
	Long count(T entity, QueryAssistor queryAssistor) throws ServiceException;

	/**
	 * 按条件查询记录集合
	 *
	 * @param entity
	 *            业务实体类或业务查询实体类
	 * @param queryAssistor
	 *            查询辅助类
	 * @return
	 * @throws ServiceException
	 */
	List<T> findAll(T entity, QueryAssistor queryAssistor) throws ServiceException;

	/**
	 * 按条件查询记录集合
	 *
	 * @param entity
	 *            业务实体类或业务查询实体类
	 * @return
	 * @throws ServiceException
	 */
	List<T> findAll(T entity) throws ServiceException;

}
