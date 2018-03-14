package com.sun.szk.base.core.service.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.sun.szk.base.core.controller.form.QueryAssistor;
import com.sun.szk.base.core.dao.BaseMapper;
import com.sun.szk.base.core.domain.BaseCreateEntity;
import com.sun.szk.base.core.domain.BaseTimeEntity;
import com.sun.szk.base.core.exception.ServiceException;
import com.sun.szk.base.core.result.DataTableResult;
import com.sun.szk.base.core.result.EasyUIResult;
import com.sun.szk.base.core.result.QueryResult;
import com.sun.szk.base.core.service.BaseService;
import com.sun.szk.base.core.utils.ChainMap;

/**
 * <p>
 * 服务支持
 * <p>
 * 为服务组件的基类，必须继承
 *
 * @param <T>
 *            该服务组件服务的数据模型，即model;
 * @author jtwu
 */
@SuppressWarnings("unchecked")
public abstract class ServiceSupport<T, M extends BaseMapper<T>> implements BaseService<T> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public abstract M getMapper();

	@Value("${tableJsLib}")
	private String tableJsLib;

	@Override
	public void save(T entity) throws ServiceException {
		if (entity instanceof BaseTimeEntity) {
			BaseTimeEntity baseEntity = (BaseTimeEntity) entity;
			baseEntity.setCreateDateTime(new Date());
			baseEntity.setModifyDateTime(baseEntity.getCreateDateTime());
		} else if (entity instanceof BaseCreateEntity) {
			BaseCreateEntity baseEntity = (BaseCreateEntity) entity;
			baseEntity.setCreateDateTime(new Date());
		}

		getMapper().save(entity);
	}

	@Override
	public void saveForSelective(T entity) throws ServiceException {
		if (entity instanceof BaseTimeEntity) {
			BaseTimeEntity baseEntity = (BaseTimeEntity) entity;
			baseEntity.setCreateDateTime(new Date());
			baseEntity.setModifyDateTime(baseEntity.getCreateDateTime());
		} else if (entity instanceof BaseCreateEntity) {
			BaseCreateEntity baseEntity = (BaseCreateEntity) entity;
			baseEntity.setCreateDateTime(new Date());
		}

		getMapper().saveForSelective(entity);
	}

	@Override
	public void update(T entity) throws ServiceException {
		if (entity instanceof BaseTimeEntity) {
			BaseTimeEntity baseEntity = (BaseTimeEntity) entity;
			baseEntity.setModifyDateTime(new Date());
		}
		getMapper().update(entity);
	}

	@Override
	public void deleteByCondition(T entity) throws ServiceException {
		getMapper().deleteByCondition((Class<T>) entity.getClass(), getValueMap(entity));
	}

	@Override
	public void deleteByCondition(T entity, QueryAssistor queryAssistor) throws ServiceException {
		getMapper().deleteByCondition((Class<T>) entity.getClass(), getValueMap(entity, queryAssistor));
	}

	public List<T> pageAll(T entity, QueryAssistor queryAssistor) throws ServiceException {
		return getMapper().pageData((Class<T>) entity.getClass(), getValueMap(queryAssistor, entity));
	}

	@Override
	public QueryResult<T> getData(T entity, QueryAssistor queryAssistor) throws ServiceException {
		QueryResult<T> qr = newQueryResult();
		qr.setData(getMapper().pageData((Class<T>) entity.getClass(), getValueMap(queryAssistor, entity)));
		qr.setRecordsTotal(getMapper().pageTotalRecord((Class<T>) entity.getClass(), getValueMap(queryAssistor,
				entity)));
		return qr;
	}

	protected <E> QueryResult<E> newQueryResult() {
		if (StringUtils.isEmpty(tableJsLib)) {
			return new EasyUIResult<>();
		} else {
			switch (tableJsLib) {
			case "DataTable":
				return new DataTableResult<>();
			case "EasyUI":
				return new EasyUIResult<>();
			default:
				return null;
			}
		}
	}

	@Override
	public void updateForSelective(T entity) throws ServiceException {
		if (entity instanceof BaseTimeEntity) {
			BaseTimeEntity baseEntity = (BaseTimeEntity) entity;
			baseEntity.setModifyDateTime(new Date());
		}
		getMapper().updateForSelective(entity);
	}

	@Override
	public Long count(T entity, QueryAssistor queryAssistor) throws ServiceException {
		return getMapper().pageTotalRecord((Class<T>) entity.getClass(), getValueMap(queryAssistor, entity));
	}

	@Override
	public List<T> findAll(T entity, QueryAssistor queryAssistor) throws ServiceException {
		return getMapper().findAll((Class<T>) entity.getClass(), getValueMap(queryAssistor, entity));
	}

	/**
	 * 实体转map
	 *
	 * @param objs
	 * @return
	 */
	protected ChainMap<String, Object> getValueMap(Object... objs) throws ServiceException {
		try {
			ChainMap<String, Object> map = new ChainMap<>();
			for (Object obj : objs) {
				if (null == obj) {
					continue;
				}
				for (Class<?> c = obj.getClass(); Object.class != c; c = c.getSuperclass()) {
					for (Field field : c.getDeclaredFields()) {
						field.setAccessible(true);
						Object value = field.get(obj);
						if (null == value) {
							continue;
						}
						if (String.class.equals(field.getType()) && StringUtils.isEmpty((String) value)) {
							continue;
						}
						map.put(field.getName(), value);

					}
				}

			}
			return map;
		} catch (Exception e) {
			throw new ServiceException("Object to Map convert Error", e);
		}

	}

	@Override
	public List<T> findAll(T entity) throws ServiceException {
		return findAll(entity, null);
	}
}
