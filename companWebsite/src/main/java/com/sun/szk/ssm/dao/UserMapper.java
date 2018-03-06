package com.sun.szk.ssm.dao;

import com.sun.szk.ssm.domain.User;
import com.sun.szk.ssm.base.controller.form.QueryAssistor;
import com.sun.szk.ssm.base.controller.form.SelectAssistor;
import com.sun.szk.ssm.base.dao.AssistorMapperProvider;
import com.sun.szk.ssm.base.dao.BaseMapper;
import com.sun.szk.ssm.base.dao.BaseMapperProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * User Mapper
 *
 */
public interface UserMapper extends BaseMapper<User> {

	@Override
	@SelectProvider(type = BaseMapperProvider.class, method = "selectById")
	User selectById(@Param(ENTITY_CLASS) Class<User> entityClass,
			@Param(ID) Serializable id);

	@SelectProvider(type = BaseMapperProvider.class, method = "pageData")
	List<User> pageData(@Param(ENTITY_CLASS) Class<User> entityClass,
			@Param(DATA) Map<String, Object> map);

	@SelectProvider(type = AssistorMapperProvider.class, method = "pageData")
	List<User> pageAssistor(@Param(ENTITY) User entity,
			@Param(SELECT_ASSISTOR) SelectAssistor selectAssistor,
			@Param(QUERY_ASSISTOR) QueryAssistor queryAssistor);

	@Override
	@SelectProvider(type = BaseMapperProvider.class, method = "findAll")
	List<User> findAll(@Param(ENTITY_CLASS) Class<User> entityClass,
			@Param(DATA) Map<String, Object> data);

	@SelectProvider(type = AssistorMapperProvider.class, method = "findAll")
	List<User> findAllByAssistor(@Param(ENTITY) User entity,
			@Param(SELECT_ASSISTOR) SelectAssistor selectAssistor,
			@Param(QUERY_ASSISTOR) QueryAssistor queryAssistor);
}
