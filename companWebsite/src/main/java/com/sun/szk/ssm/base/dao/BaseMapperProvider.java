package com.sun.szk.ssm.base.dao;

import static com.sun.szk.ssm.base.dao.BaseMapper.DATA;
import static com.sun.szk.ssm.base.dao.BaseMapper.ENTITY;
import static com.sun.szk.ssm.base.dao.BaseMapper.ENTITY_CLASS;
import static com.sun.szk.ssm.base.dao.BaseMapper.ID;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SqlBuilder;

import com.sun.szk.ssm.base.controller.form.OperatorAssistor;
import com.sun.szk.ssm.base.controller.form.SQLJoinAssistor;
import com.sun.szk.ssm.base.exception.DaoException;
import com.sun.szk.ssm.base.exception.UtilException;
import com.sun.szk.ssm.base.utils.JPAUtils;

/**
 * Created by jtwu on 2015/4/21.
 */
@SuppressWarnings("unchecked")
public class BaseMapperProvider<T> extends SqlBuilder {
	private Class<?> entityClass;
	protected String limit;

	/**
	 * 查询所有数据sql
	 *
	 * @param param
	 *            参数
	 * @return sql
	 * @throws com.sun.szk.ssm.base.exception.DaoException
	 */
	public String findAll(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		if (param.containsKey("distinct")) {
			SELECT_DISTINCT("t.*");
		} else {
			SELECT("t.*");
		}
		FROM(getTableNameWithAlias(entityClass));
		createAllWhere((Map<String, Object>) param.get(DATA), false);
		return StringUtils.join(SQL(), limit);
	}

	protected void createAllWhere(Map<String, Object> param, boolean usePage) throws DaoException {
		createAllWhere(param, usePage, false);
	}

	protected void createAllWhere(Map<String, Object> param, boolean usePage, boolean isCount) throws DaoException {
		if (MapUtils.isEmpty(param)) {
			return;
		}
		try {
			Map<String, OperatorAssistor> operatorMap = new HashMap<>();
			if (param.containsKey("operatorAssistors")) {
				List<OperatorAssistor> operatorAssistors = (List<OperatorAssistor>) param.get("operatorAssistors");
				for (OperatorAssistor operatorAssistor : operatorAssistors) {
					if (null != operatorAssistor.getValues()) {
						switch (operatorAssistor.getOperator()) {
						case IN: {
							List<String> values = new ArrayList<>();
							for (Object value : operatorAssistor.getValues()) {
								values.add("'" + String.valueOf(value).replaceAll("'", "''") + "'");
							}
							WHERE(StringUtils.join("t.", operatorAssistor.getColumn(), " in (",
									StringUtils.join(values, ","), ")"));
						}
						default:
						}
					}
					operatorMap.put(operatorAssistor.getColumn(), operatorAssistor);
				}
			}

			for (Field field : JPAUtils.getAllFields(entityClass)) {
				if (null != field.getAnnotation(Transient.class)) {
					continue;
				}
				// 处理表达式
				if (param.containsKey(field.getName()) && !field.getType().isAssignableFrom(Class.class)) {
					if (operatorMap.containsKey(field.getName())) {
						switch (((OperatorAssistor) operatorMap.get(field.getName())).getOperator()) {
						case LIKE: {
							WHERE(StringUtils.join("t.", field.getName(), " like CONCAT('%',#{",
									StringUtils.join(DATA, ".", field.getName()), "}, '%')"));
							break;
						}
						case LESS_THEN: {
							WHERE(StringUtils.join("t.", field.getName(), " < #{",
									StringUtils.join(DATA, ".", field.getName()), "}"));
							break;
						}
						case MORE_THEN: {
							WHERE(StringUtils.join("t.", field.getName(), " > #{",
									StringUtils.join(DATA, ".", field.getName()), "}"));
							break;
						}
						case LESS_EQUAL: {
							WHERE(StringUtils.join("t.", field.getName(), " <= #{",
									StringUtils.join(DATA, ".", field.getName()), "}"));
							break;
						}
						case MORE_EQUAL: {
							WHERE(StringUtils.join("t.", field.getName(), " >= #{",
									StringUtils.join(DATA, ".", field.getName()), "}"));
							break;
						}
						case IN: {
							String value = Arrays
									.toString(((OperatorAssistor) operatorMap.get(field.getName())).getValues());
							WHERE(StringUtils.join("t.", field.getName(), " in (",
									value.substring(1, value.length() - 2), ")"));
							break;
						}
						}
					} else {
						WHERE(StringUtils.join("t.",
								getEqualsValue(field.getName(), StringUtils.join(DATA, ".", field.getName()))));
					}
				}
			}
			parseQueryAssistor(param, usePage, isCount);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * 处理查询扩展类
	 *
	 * @param param
	 */
	private void parseQueryAssistor(Map<String, Object> param, Boolean usePage, Boolean isCount) {
		if (param.containsKey("orderBy") && !isCount) {
			LinkedHashMap<String, String> orderByMap = (LinkedHashMap<String, String>) param.get("orderBy");
			for (Entry<String, String> e : orderByMap.entrySet()) {
				String column = e.getKey();
				String order = e.getValue();
				if (column.contains(".")) {
					ORDER_BY(column + " " + order);
				} else {
					ORDER_BY("t." + column + " " + order);
				}
			}
		}
		if (param.containsKey("whereSqls")) {
			List<String> whereSqls = (List<String>) param.get("whereSqls");
			for (String whereSql : whereSqls) {
				WHERE(whereSql);
			}
		}
		if (usePage && param.containsKey("rows")) {
			Integer page = (Integer) param.get("page");
			Integer startIndex = (Integer) param.get("startIndex");
			int rows = (Integer) param.get("rows");
			if (page != null) {
				limit = " limit " + ((page - 1) * rows) + ", " + rows;
			} else if (startIndex != null) {
				limit = " limit " + startIndex + ", " + rows;
			} else {
				limit = " limit " + rows;
			}
		}
		if (param.containsKey("sqlJoinAssistors")) {
			LinkedList<SQLJoinAssistor> sqlJoinAssistors = (LinkedList<SQLJoinAssistor>) param.get("sqlJoinAssistors");
			for (SQLJoinAssistor sqlJoinAssistor : sqlJoinAssistors) {
				switch (sqlJoinAssistor.getJoinType()) {
				case JOIN: {
					JOIN(sqlJoinAssistor.getJoinSql());
					if (StringUtils.isNotEmpty(sqlJoinAssistor.getSelectColumns()) && !isCount) {
						SELECT(sqlJoinAssistor.getSelectColumns());
					}
					break;
				}
				case INNER_JOIN: {
					INNER_JOIN(sqlJoinAssistor.getJoinSql());
					if (StringUtils.isNotEmpty(sqlJoinAssistor.getSelectColumns()) && !isCount) {
						SELECT(sqlJoinAssistor.getSelectColumns());
					}
					break;
				}
				case LEFT_OUTER_JOIN: {
					LEFT_OUTER_JOIN(sqlJoinAssistor.getJoinSql());
					if (StringUtils.isNotEmpty(sqlJoinAssistor.getSelectColumns()) && !isCount) {
						SELECT(sqlJoinAssistor.getSelectColumns());
					}
					break;
				}
				case RIGHT_OUTER_JOIN: {
					RIGHT_OUTER_JOIN(sqlJoinAssistor.getJoinSql());
					if (StringUtils.isNotEmpty(sqlJoinAssistor.getSelectColumns()) && !isCount) {
						SELECT(sqlJoinAssistor.getSelectColumns());
					}
					break;
				}
				}
			}
		}
	}

	/**
	 * 查询所有数据sql
	 *
	 * @param param
	 *            参数
	 * @return sql
	 * @throws DaoException
	 */
	public String selectById(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		SELECT("*");
		FROM(getTableName(entityClass));
		try {
			WHERE(getEqualsValue(JPAUtils.getIdField(entityClass).getName(), ID));
		} catch (UtilException e) {
			throw new DaoException(e);
		}
		return SQL();
	}

	/**
	 * 获取比较值
	 *
	 * @param column
	 *            列名
	 * @param value
	 *            值
	 * @return
	 */
	private String getEqualsValue(String column, String value) {
		return StringUtils.join(column, " = #{", value, "}");
	}

	/**
	 * 生成sql开始 1.判断是否传入class 2.BEGIN
	 *
	 * @param param
	 * @throws DaoException
	 */
	protected void beginWithClass(Map<String, Object> param) throws DaoException {
		if (null == param.get(ENTITY_CLASS)) {
			throw new DaoException(StringUtils.join("获取实体类型失败 entityClass 为空"));
		}
		BEGIN();
		entityClass = (Class<?>) param.get(ENTITY_CLASS);
	}

	/**
	 * 保存sql
	 *
	 * @param param
	 *            参数
	 * @return sql
	 * @throws DaoException
	 */
	public String save(Map<String, Object> param) throws Exception {
		Object entity = param.get(ENTITY);
		entityClass = entity.getClass();
		BEGIN();
		INSERT_INTO(getTableName(entityClass));

		Field idField = null;
		for (Field field : JPAUtils.getAllFields(entityClass)) {
			field.setAccessible(true);
			if (field.getAnnotation(Transient.class) != null) {
				continue;
			}
			if (field.getAnnotation(Id.class) != null) {
				idField = field;
				// 处理主键
				if (String.class.equals(field.getType())) {
					if (StringUtils.isEmpty((String) field.get(entity))) {
						String id = java.util.UUID.randomUUID().toString();
						field.set(entity, id);
					}
				}
			}
			VALUES(field.getName(), StringUtils.join("#{", ENTITY, ".", field.getName(), "}"));
		}
		if (null == idField) {
			throw new DaoException(StringUtils.join(entityClass.getName(), "实体未配置@Id "));
		}
		return SQL();
	}

	public String saveForSelective(Map<String, Object> param) throws Exception {
		final Object entity = param.get(ENTITY);
		entityClass = entity.getClass();
		BEGIN();
		INSERT_INTO(getTableName(entityClass));

		Field idField = null;
		for (Field field : JPAUtils.getAllFields(entityClass)) {
			field.setAccessible(true);
			if (field.getAnnotation(Transient.class) != null) {
				continue;
			}
			if (field.getAnnotation(Id.class) != null) {
				idField = field;
				// 处理主键
				if (String.class.equals(field.getType())) {
					if (StringUtils.isEmpty((String) field.get(entity))) {
						String id = java.util.UUID.randomUUID().toString();
						field.set(entity, id);
					}
				}
			}
			if (field.get(entity) != null) {
				VALUES(field.getName(), StringUtils.join("#{", ENTITY, ".", field.getName(), "}"));
			}
		}
		if (null == idField) {
			throw new DaoException(StringUtils.join(entityClass.getName(), "实体未配置@Id "));
		}
		return SQL();
	}

	public String update(Map<String, Object> param) throws Exception {
		Object entity = param.get(ENTITY);
		entityClass = entity.getClass();
		BEGIN();
		UPDATE(getTableName(entityClass));
		Field idField = null;
		for (Field field : JPAUtils.getAllFields(entityClass)) {
			field.setAccessible(true);
			// 处理主键
			if (null == field.getAnnotation(Id.class)) {
				SET(getEqualsValue(field.getName(), StringUtils.join(ENTITY, ".", field.getName())));
			} else {
				idField = field;
				WHERE(getEqualsValue(field.getName(), StringUtils.join(ENTITY, ".", field.getName())));
			}
		}
		if (null == idField) {
			throw new DaoException(StringUtils.join(entityClass.getName(), "实体未配置@Id "));
		}

		return SQL();

	}

	public String updateForSelective(Map<String, Object> param) throws Exception {
		Object entity = param.get(ENTITY);
		entityClass = entity.getClass();
		BEGIN();
		UPDATE(getTableName(entityClass));
		Field idField = null;
		for (Field field : JPAUtils.getAllFields(entityClass)) {
			field.setAccessible(true);
			// 处理主键
			if (null == field.getAnnotation(Id.class)) {
				if (field.get(entity) != null) {
					SET(getEqualsValue(field.getName(), StringUtils.join(ENTITY, ".", field.getName())));
				}
			} else {
				idField = field;
				WHERE(getEqualsValue(field.getName(), StringUtils.join(ENTITY, ".", field.getName())));
			}
		}
		if (null == idField) {
			throw new DaoException(StringUtils.join(entityClass.getName(), "实体未配置@Id "));
		}
		return SQL();
	}

	/**
	 * 获取表名
	 *
	 * @param entityClass
	 *            实体类
	 * @return
	 * @throws DaoException
	 */
	protected String getTableName(Class<?> entityClass) throws DaoException {
		Annotation table = entityClass.getAnnotation(Table.class);
		if (null == table) {
			throw new DaoException(StringUtils.join("实体未配置Table注解 entityClass =", entityClass.getName()));
		}
		String tableName = ((Table) table).name();
		if (StringUtils.isEmpty(tableName)) {
			throw new DaoException(StringUtils.join("实体的Table注解未配置name属性 entityClass =", entityClass.getName()));
		}
		return tableName;
	}

	protected String getTableNameWithAlias(Class<?> entityClass) throws DaoException {
		return StringUtils.join(getTableName(entityClass), " t");
	}

	public String pageData(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		if (param.containsKey("distinct")) {
			SELECT_DISTINCT("t.*");
		} else {
			SELECT("t.*");
		}
		FROM(getTableNameWithAlias(entityClass));
		createAllWhere((Map<String, Object>) param.get(DATA), true);
		return StringUtils.join(SQL(), limit);
	}

	public String pageTotalRecord(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		SELECT("count(*)");
		FROM(getTableNameWithAlias(entityClass));
		createAllWhere((Map<String, Object>) param.get(DATA), false, true);
		return StringUtils.join(SQL(), limit);
	}

	public String delete(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		DELETE_FROM(getTableName(entityClass));
		try {
			WHERE(getEqualsValue(JPAUtils.getIdField(entityClass).getName(), ID));
		} catch (UtilException e) {
			throw new DaoException(e);
		}
		return SQL();
	}

	public String deleteByCondition(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		createAllWhere((Map<String, Object>) param.get(DATA), false);
		DELETE_FROM(StringUtils.join(getTableName(entityClass), " t"));
		return SQL().replaceFirst("DELETE FROM", "DELETE t FROM");
	}
}
