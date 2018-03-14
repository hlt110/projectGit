package com.sun.szk.base.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.sun.szk.base.core.controller.form.QueryAssistor;
import com.sun.szk.base.core.controller.form.SelectAssistor;

/**
 * Created by jtwu on 2015/4/21. mybatis通用处理类
 */

public interface BaseMapper<T> {
	String ENTITY_CLASS = "entityClass";
	String ENTITY = "entity";
	String ID = "id";
	String DATA = "data";
	String SELECT_ASSISTOR = "SelectAssistor";
	String QUERY_ASSISTOR = "QueryAssistor";

	/**
	 * 插入一条记录
	 *
	 * @param entity
	 *            业务实体
	 */
	@InsertProvider(type = BaseMapperProvider.class, method = "save")
	void save(@Param(ENTITY) T entity);

	/**
	 * 插入非空字段
	 *
	 * @param entity
	 *            业务实体
	 */
	@InsertProvider(type = BaseMapperProvider.class, method = "saveForSelective")
	void saveForSelective(@Param(ENTITY) T entity);

	/**
	 * 更新一条记录
	 *
	 * @param entity
	 *            业务实体
	 */
	@UpdateProvider(type = BaseMapperProvider.class, method = "update")
	void update(@Param(ENTITY) T entity);

	/**
	 * 更新非空字段
	 *
	 * @param entity
	 *            业务实体
	 */
	@UpdateProvider(type = BaseMapperProvider.class, method = "updateForSelective")
	void updateForSelective(@Param(ENTITY) T entity);

	/**
	 * 删除
	 *
	 * @param entityClass
	 *            实体类型
	 * @param id
	 *            主键
	 */
	@DeleteProvider(type = BaseMapperProvider.class, method = "delete")
	void delete(@Param(ENTITY_CLASS) Class<?> entityClass, @Param(ID) Serializable id);

	/**
	 * 根据条件删除
	 *
	 * @param map
	 *            删除条件
	 */
	@DeleteProvider(type = BaseMapperProvider.class, method = "deleteByCondition")
	void deleteByCondition(@Param(ENTITY_CLASS) Class<T> entityClass, @Param(DATA) Map<String, Object> map);

	@DeleteProvider(type = AssistorMapperProvider.class, method = "deleteByCondition")
	void deleteByAssistor(@Param(ENTITY) T entity, @Param(SELECT_ASSISTOR) SelectAssistor selectAssistor);

	/**
	 * 根据主键查询 此处来写注解，在子类里面生效
	 *
	 * @param entityClass
	 *            业务实体
	 * @param id
	 *            业务主键
	 */
	// in subclass @SelectProvider(type = BaseMapperProvider.class, method = "selectById")
	T selectById(@Param(ENTITY_CLASS) Class<T> entityClass, @Param(ID) Serializable id);

	/**
	 * 获取分页记录总数 此处来写注解，在子类里面生效
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	// in subclass @SelectProvider(type = BaseMapperProvider.class, method = "pageData")
	List<T> pageData(@Param(ENTITY_CLASS) Class<T> entityClass, @Param(DATA) Map<String, Object> map);

	// in subclass @SelectProvider(type = AssistorMapperProvider.class, method = "pageData")
	List<T> pageAssistor(@Param(ENTITY) T entity, @Param(SELECT_ASSISTOR) SelectAssistor selectAssistor,
			@Param(QUERY_ASSISTOR) QueryAssistor queryAssistor);

	/**
	 * 统计分页记录总数
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SelectProvider(type = BaseMapperProvider.class, method = "pageTotalRecord")
	Long pageTotalRecord(@Param(ENTITY_CLASS) Class<T> entityClass, @Param(DATA) Map<String, Object> map);

	@SelectProvider(type = AssistorMapperProvider.class, method = "pageTotalRecord")
	Long totalRecordAssistor(@Param(ENTITY) T entity, @Param(SELECT_ASSISTOR) SelectAssistor selectAssistor);

	/**
	 * 按条件查询记录集合 此处来写注解，在子类里面生效
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	// in subclass @SelectProvider(type = BaseMapperProvider.class, method = "findAll")
	List<T> findAll(@Param(ENTITY_CLASS) Class<T> entityClass, @Param(DATA) Map<String, Object> data);

	// in subclass @SelectProvider(type = AssistorMapperProvider.class, method = "findAll")
	List<T> findAllByAssistor(@Param(ENTITY) T entity, @Param(SELECT_ASSISTOR) SelectAssistor selectAssistor,
			@Param(QUERY_ASSISTOR) QueryAssistor queryAssistor);
}