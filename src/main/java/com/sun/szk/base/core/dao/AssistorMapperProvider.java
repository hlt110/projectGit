package com.sun.szk.base.core.dao;

import com.sun.szk.base.core.controller.form.OperatorAssistor;
import com.sun.szk.base.core.controller.form.QueryAssistor;
import com.sun.szk.base.core.controller.form.SQLJoinAssistor;
import com.sun.szk.base.core.controller.form.SelectAssistor;
import com.sun.szk.base.core.exception.DaoException;
import com.sun.szk.base.core.exception.UtilException;
import com.sun.szk.base.core.utils.JPAUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SqlBuilder;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

import static com.sun.szk.base.core.dao.BaseMapper.*;


/**
 * Created by jtwu on 2015/4/21.
 */
public class AssistorMapperProvider {
	protected static final String MAIN_TABLE_ALIAS = "t";
	protected static final String MAIN_TABLE_DOT = MAIN_TABLE_ALIAS + ".";
	protected Class<?> entityClass;
	protected Object entity;
	protected QueryAssistor queryAssistor;
	protected SelectAssistor selectAssistor;
	protected String limit;

	/**
	 * 查询所有数据sql
	 *
	 * @param param
	 *            参数
	 * @return sql
	 * @throws com.sun.szk.base.core.exception.DaoException
	 */
	public String findAll(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		if (param.containsKey("distinct")) {
			SqlBuilder.SELECT_DISTINCT(MAIN_TABLE_DOT + "*");
		} else {
			SqlBuilder.SELECT(MAIN_TABLE_DOT + "*");
		}
		SqlBuilder.FROM(getTableNameWithAlias(entityClass));
		createAllWhere(false, false);
		return SqlBuilder.SQL() + limit;
	}

	protected void createAllWhere(boolean usePage, boolean isCount) throws DaoException {
		parseSelectAssistor(isCount);
		parseQueryAssistor(usePage, isCount);
	}

	protected void parseSelectAssistor(boolean isCount) throws DaoException {
		if (selectAssistor == null) {
			return;
		}
		try {
			parseAliasColumn();
			parseOperatorAssistor();
			parseWhereSqls();
			parseSQLJoinAssistor(isCount);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	protected void parseAliasColumn() {
		List<String> aliasColumnList = selectAssistor.getAliasColumnList();
		if (CollectionUtils.isNotEmpty(aliasColumnList)) {
			for (String aliasColumn : aliasColumnList) {
				SqlBuilder.SELECT(aliasColumn);
			}
		}
	}

	public static void WHERE(String... args) {
		StringBuilder conditions = new StringBuilder();
		for (String arg : args) {
			conditions.append(arg);
		}
		SqlBuilder.WHERE(conditions.toString());
	}

	protected void parseOperatorAssistor() throws IllegalAccessException {
		Map<String, OperatorAssistor> operatorMap = new HashMap<>();
		List<OperatorAssistor> operatorAssistorList = selectAssistor.getOperatorAssistorList();
		if (CollectionUtils.isNotEmpty(operatorAssistorList)) {
			for (OperatorAssistor operatorAssistor : operatorAssistorList) {
				if (null != operatorAssistor.getValues()) {
					switch (operatorAssistor.getOperator()) {
					case IN: {
						List<String> values = new ArrayList<>();
						for (Object value : operatorAssistor.getValues()) {
							values.add("'" + String.valueOf(value).replaceAll("'", "''") + "'");
						}
						WHERE(MAIN_TABLE_DOT, operatorAssistor.getColumn(), " in (", StringUtils.join(values, ","),
								")");
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
			field.setAccessible(true);
			Object value = field.get(entity);
			if (value == null) {
				continue;
			}
			if (value instanceof String && StringUtils.isEmpty((String) value)) {
				continue;
			}
			// 处理表达式
			if (field.getType().isAssignableFrom(Class.class)) {
				continue;
			}
			String fieldName = field.getName();
			OperatorAssistor operatorAssistor = operatorMap.get(fieldName);
			if (operatorAssistor != null && operatorAssistor.getOperator() != null) {
				switch (operatorAssistor.getOperator()) {
				case LIKE: {
					WHERE(MAIN_TABLE_DOT + fieldName + " like CONCAT('%'+#{" + ENTITY + "." + fieldName + "}, '%')");
					break;
				}
				case LESS_THEN: {
					WHERE(MAIN_TABLE_DOT + fieldName + " < #{" + ENTITY + "." + fieldName + "}");
					break;
				}
				case MORE_THEN: {
					WHERE(MAIN_TABLE_DOT + fieldName + " > #{" + ENTITY + "." + fieldName + "}");
					break;
				}
				case LESS_EQUAL: {
					WHERE(MAIN_TABLE_DOT + fieldName + " <= #{" + ENTITY + "." + fieldName + "}");
					break;
				}
				case MORE_EQUAL: {
					WHERE(MAIN_TABLE_DOT + fieldName + " >= #{" + ENTITY + "." + fieldName + "}");
					break;
				}
				default: {
					break;
				}
				}
			} else {
				WHERE(MAIN_TABLE_DOT + getEqualsValue(fieldName, ENTITY + "." + fieldName));
			}
		}
	}

	protected void parseWhereSqls() {
		List<String> whereSqlList = selectAssistor.getWhereSqlList();
		if (CollectionUtils.isNotEmpty(whereSqlList)) {
			for (String whereSql : whereSqlList) {
				WHERE(whereSql);
			}
		}
	}

	protected void parseSQLJoinAssistor(boolean isCount) {
		LinkedList<SQLJoinAssistor> sqlJoinAssistorList = selectAssistor.getSqlJoinAssistorList();
		if (CollectionUtils.isNotEmpty(sqlJoinAssistorList)) {
			for (SQLJoinAssistor sqlJoinAssistor : sqlJoinAssistorList) {
				switch (sqlJoinAssistor.getJoinType()) {
				case JOIN: {
					SqlBuilder.JOIN(sqlJoinAssistor.getJoinSql());
					if (StringUtils.isNotEmpty(sqlJoinAssistor.getSelectColumns()) && !isCount) {
						SqlBuilder.SELECT(sqlJoinAssistor.getSelectColumns());
					}
					break;
				}
				case INNER_JOIN: {
					SqlBuilder.INNER_JOIN(sqlJoinAssistor.getJoinSql());
					if (StringUtils.isNotEmpty(sqlJoinAssistor.getSelectColumns()) && !isCount) {
						SqlBuilder.SELECT(sqlJoinAssistor.getSelectColumns());
					}
					break;
				}
				case LEFT_OUTER_JOIN: {
					SqlBuilder.LEFT_OUTER_JOIN(sqlJoinAssistor.getJoinSql());
					if (StringUtils.isNotEmpty(sqlJoinAssistor.getSelectColumns()) && !isCount) {
						SqlBuilder.SELECT(sqlJoinAssistor.getSelectColumns());
					}
					break;
				}
				case RIGHT_OUTER_JOIN: {
					SqlBuilder.RIGHT_OUTER_JOIN(sqlJoinAssistor.getJoinSql());
					if (StringUtils.isNotEmpty(sqlJoinAssistor.getSelectColumns()) && !isCount) {
						SqlBuilder.SELECT(sqlJoinAssistor.getSelectColumns());
					}
					break;
				}
				}
			}
		}
	}

	/**
	 * 处理查询扩展类
	 *
	 * @param
	 */
	private void parseQueryAssistor(boolean usePage, boolean isCount) {
		if (queryAssistor == null) {
			return;
		}
		parseOrderBy(isCount);
		parseLimit(usePage);
	}

	protected void parseOrderBy(boolean isCount) {
		LinkedHashMap<String, String> orderByMap = queryAssistor.getOrderBy();
		if (MapUtils.isNotEmpty(orderByMap) && !isCount) {
			for (Entry<String, String> e : orderByMap.entrySet()) {
				String column = e.getKey();
				String order = e.getValue();
				if (column.contains(".")) {
					SqlBuilder.ORDER_BY(column + " " + order);
				} else {
					SqlBuilder.ORDER_BY(MAIN_TABLE_DOT + column + " " + order);
				}
			}
		}
	}

	protected void parseLimit(boolean usePage) {
		if (usePage) {
			Integer page = queryAssistor.getPage();
			Integer startIndex = queryAssistor.getStartIndex();
			int rows = queryAssistor.getRows();
			if (page != null) {
				limit = " limit " + ((page - 1) * rows) + ", " + rows;
			} else if (startIndex != null) {
				limit = " limit " + startIndex + ", " + rows;
			} else {
				limit = " limit " + rows;
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
		SqlBuilder.SELECT("*");
		SqlBuilder.FROM(getTableName(entityClass));
		try {
			WHERE(getEqualsValue(JPAUtils.getIdField(entityClass).getName(), ID));
		} catch (UtilException e) {
			throw new DaoException(e);
		}
		return SqlBuilder.SQL();
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
		return column + " = #{" + value + "}";
	}

	/**
	 * 生成sql开始 1.判断是否传入class 2.BEGIN
	 *
	 * @param param
	 * @throws DaoException
	 */
	protected void beginWithClass(Map<String, Object> param) throws DaoException {
		if (param.containsKey(ENTITY_CLASS)) {
			entityClass = (Class<?>) param.get(ENTITY_CLASS);
		}
		if (param.containsKey(ENTITY)) {
			entity = param.get(ENTITY);
		}
		if (entity == null && entityClass == null) {
			throw new DaoException("获取实体类型失败 entity && entityClass 为空");
		}
		if (entity != null && entityClass == null) {
			entityClass = entity.getClass();
		}
		if (param.containsKey(QUERY_ASSISTOR)) {
			queryAssistor = (QueryAssistor) param.get(QUERY_ASSISTOR);
		}
		if (param.containsKey(SELECT_ASSISTOR)) {
			selectAssistor = (SelectAssistor) param.get(SELECT_ASSISTOR);
		}
		SqlBuilder.BEGIN();
	}

	public String update(Map<String, Object> param) throws Exception {
		Object entity = param.get(ENTITY);
		entityClass = entity.getClass();
		SqlBuilder.BEGIN();
		SqlBuilder.UPDATE(getTableName(entityClass));
		Field idField = null;
		for (Field field : JPAUtils.getAllFields(entityClass)) {
			field.setAccessible(true);
			// 处理主键
			if (null == field.getAnnotation(Id.class)) {
				SqlBuilder.SET(getEqualsValue(field.getName(), ENTITY + "." + field.getName()));
			} else {
				idField = field;
				WHERE(getEqualsValue(field.getName(), ENTITY + "." + field.getName()));
			}
		}
		if (null == idField) {
			throw new DaoException(entityClass.getName() + "实体未配置@Id ");
		}

		return SqlBuilder.SQL();

	}

	public String updateForSelective(Map<String, Object> param) throws Exception {
		Object entity = param.get(ENTITY);
		entityClass = entity.getClass();
		SqlBuilder.BEGIN();
		SqlBuilder.UPDATE(getTableName(entityClass));
		Field idField = null;
		for (Field field : JPAUtils.getAllFields(entityClass)) {
			field.setAccessible(true);
			// 处理主键
			if (null == field.getAnnotation(Id.class)) {
				if (field.get(entity) != null) {
					SqlBuilder.SET(getEqualsValue(field.getName(), ENTITY + "." + field.getName()));
				}
			} else {
				idField = field;
				WHERE(getEqualsValue(field.getName(), ENTITY + "." + field.getName()));
			}
		}
		if (null == idField) {
			throw new DaoException(entityClass.getName() + "实体未配置@Id ");
		}
		return SqlBuilder.SQL();
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
		for (Class<?> c = entityClass; !Object.class.equals(c); c = c.getSuperclass()) {
			Annotation table = c.getAnnotation(Table.class);
			if (null == table) {
				continue;
			}
			String tableName = ((Table) table).name();
			if (StringUtils.isEmpty(tableName)) {
				throw new DaoException("实体的Table注解未配置name属性 entityClass =" + entityClass.getName());
			}
			return tableName;
		}
		throw new DaoException("实体未配置Table注解 entityClass =" + entityClass.getName());
	}

	protected String getTableNameWithAlias(Class<?> entityClass) throws DaoException {
		return getTableName(entityClass) + " " + MAIN_TABLE_ALIAS;
	}

	public String pageData(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		if (param.containsKey("distinct")) {
			SqlBuilder.SELECT_DISTINCT(MAIN_TABLE_DOT + "*");
		} else {
			SqlBuilder.SELECT(MAIN_TABLE_DOT + "*");
		}
		SqlBuilder.FROM(getTableNameWithAlias(entityClass));
		createAllWhere(true, false);
		return StringUtils.join(SqlBuilder.SQL(), limit);
	}

	public String pageTotalRecord(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		SqlBuilder.SELECT("count(*)");
		SqlBuilder.FROM(getTableNameWithAlias(entityClass));
		createAllWhere(false, true);
		return SqlBuilder.SQL();
	}

	public String delete(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		SqlBuilder.DELETE_FROM(getTableName(entityClass));
		try {
			WHERE(getEqualsValue(JPAUtils.getIdField(entityClass).getName(), ID));
		} catch (UtilException e) {
			throw new DaoException(e);
		}
		return SqlBuilder.SQL();
	}

	public String deleteByCondition(Map<String, Object> param) throws DaoException {
		beginWithClass(param);
		createAllWhere(false, false);
		SqlBuilder.DELETE_FROM(getTableName(entityClass) + " " + MAIN_TABLE_ALIAS);
		return SqlBuilder.SQL().replaceFirst("DELETE FROM", "DELETE " + MAIN_TABLE_ALIAS + " FROM");
	}
}
