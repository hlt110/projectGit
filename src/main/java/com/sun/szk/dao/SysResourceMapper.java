package com.sun.szk.dao;

import com.sun.szk.domain.SysResource;
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
 * SysResource Mapper
 *
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

	@Override
	@SelectProvider(type = BaseMapperProvider.class, method = "selectById")
	SysResource selectById(@Param(ENTITY_CLASS) Class<SysResource> entityClass,
			@Param(ID) Serializable id);

	@SelectProvider(type = BaseMapperProvider.class, method = "pageData")
	List<SysResource> pageData(@Param(ENTITY_CLASS) Class<SysResource> entityClass,
			@Param(DATA) Map<String, Object> map);

	@SelectProvider(type = AssistorMapperProvider.class, method = "pageData")
	List<SysResource> pageAssistor(@Param(ENTITY) SysResource entity,
			@Param(SELECT_ASSISTOR) SelectAssistor selectAssistor,
			@Param(QUERY_ASSISTOR) QueryAssistor queryAssistor);

	@Override
	@SelectProvider(type = BaseMapperProvider.class, method = "findAll")
	List<SysResource> findAll(@Param(ENTITY_CLASS) Class<SysResource> entityClass,
			@Param(DATA) Map<String, Object> data);

	@SelectProvider(type = AssistorMapperProvider.class, method = "findAll")
	List<SysResource> findAllByAssistor(@Param(ENTITY) SysResource entity,
			@Param(SELECT_ASSISTOR) SelectAssistor selectAssistor,
			@Param(QUERY_ASSISTOR) QueryAssistor queryAssistor);
}
