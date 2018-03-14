package com.sun.szk.dao;

import com.sun.szk.domain.SysUser;
import com.sun.szk.base.core.controller.form.QueryAssistor;
import com.sun.szk.base.core.controller.form.SelectAssistor;
import com.sun.szk.base.core.dao.AssistorMapperProvider;
import com.sun.szk.base.core.dao.BaseMapper;
import com.sun.szk.base.core.dao.BaseMapperProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * SysUser Mapper
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

	@Override
	@SelectProvider(type = BaseMapperProvider.class, method = "selectById")
	SysUser selectById(@Param(ENTITY_CLASS) Class<SysUser> entityClass,
			@Param(ID) Serializable id);

	@SelectProvider(type = BaseMapperProvider.class, method = "pageData")
	List<SysUser> pageData(@Param(ENTITY_CLASS) Class<SysUser> entityClass,
			@Param(DATA) Map<String, Object> map);

	@SelectProvider(type = AssistorMapperProvider.class, method = "pageData")
	List<SysUser> pageAssistor(@Param(ENTITY) SysUser entity,
			@Param(SELECT_ASSISTOR) SelectAssistor selectAssistor,
			@Param(QUERY_ASSISTOR) QueryAssistor queryAssistor);

	@Override
	@SelectProvider(type = BaseMapperProvider.class, method = "findAll")
	List<SysUser> findAll(@Param(ENTITY_CLASS) Class<SysUser> entityClass,
			@Param(DATA) Map<String, Object> data);

	@SelectProvider(type = AssistorMapperProvider.class, method = "findAll")
	List<SysUser> findAllByAssistor(@Param(ENTITY) SysUser entity,
			@Param(SELECT_ASSISTOR) SelectAssistor selectAssistor,
			@Param(QUERY_ASSISTOR) QueryAssistor queryAssistor);
}
