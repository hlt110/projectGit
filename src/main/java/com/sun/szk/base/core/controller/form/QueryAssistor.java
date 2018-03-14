package com.sun.szk.base.core.controller.form;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sun.szk.base.core.convert.PageConvert;
import com.sun.szk.base.core.utils.SpringContextUtils;

/**
 * Created by jtwu on 2015/1/13.
 */
public class QueryAssistor {

	private Integer startIndex;
	// 当前页
	private Integer page;
	// 页面大小
	private int rows = 10;

	// 排序字段集合
	private LinkedHashMap<String, String> orderBy;
	// sql join 辅助类
	private LinkedList<SQLJoinAssistor> sqlJoinAssistors;
	// sql 运算符辅助类
	public List<OperatorAssistor> operatorAssistors;
	// 扩展的whereSql
	public List<String> whereSqls;

	@Deprecated
	public QueryAssistor addJoinAssistors(SQLJoinAssistor sqlJoinAssistor) {
		if (sqlJoinAssistors == null) {
			sqlJoinAssistors = new LinkedList<>();
		}
		sqlJoinAssistors.add(sqlJoinAssistor);
		return this;
	}

	@Deprecated
	public LinkedList<SQLJoinAssistor> getSqlJoinAssistors() {
		return sqlJoinAssistors;
	}

	@Deprecated
	public QueryAssistor addJoinAssistors(String selectColumns, SQLJoinAssistor.JoinType joinType, String joinSql) {
		return addJoinAssistors(new SQLJoinAssistor(selectColumns, joinType, joinSql));
	}

	@Deprecated
	public List<String> getWhereSqls() {
		return whereSqls;
	}

	@Deprecated
	public QueryAssistor addOrderBy(String key, String value) {
		if (orderBy == null) {
			orderBy = new LinkedHashMap<>();
		}
		orderBy.put(key, value);
		return this;
	}

	public QueryAssistor addOrderByAsc(String field) {
		return addOrderBy(field, "asc");
	}

	public QueryAssistor addOrderByDesc(String field) {
		return addOrderBy(field, "desc");
	}

	public LinkedHashMap<String, String> getOrderBy() {
		return orderBy;
	}

	public QueryAssistor setLimit(int startIndex, int pageSize) {
		this.startIndex = startIndex;
		this.rows = pageSize;
		return this;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public Integer getPage() {
		return page;
	}

	public QueryAssistor setPage(int page) {
		this.page = page;
		return this;
	}

	public int getRows() {
		return rows;
	}

	public QueryAssistor setRows(int rows) {
		this.rows = rows;
		return this;
	}

	@Deprecated
	public QueryAssistor addOperatorAssistor(String column, OperatorAssistor.operatorType operatorType,
			Object... values) {
		if (StringUtils.isEmpty(column) || null == operatorType) {
			return this;
		}
		if (operatorAssistors == null) {
			operatorAssistors = new ArrayList<>();
		}
		operatorAssistors.add(new OperatorAssistor(column, operatorType, values));
		return this;
	}

	@Deprecated
	public QueryAssistor addWhereSql(String whereSql) {
		if (whereSqls == null) {
			whereSqls = new ArrayList<>();
		}
		whereSqls.add(whereSql);
		return this;
	}

	@Deprecated
	public QueryAssistor addSqlJoinAssistors(String selectColumns, SQLJoinAssistor.JoinType joinType, String joinSql) {
		if (StringUtils.isEmpty(joinSql) || null == joinType) {
			return this;
		}
		if (sqlJoinAssistors == null) {
			sqlJoinAssistors = new LinkedList<>();
		}
		sqlJoinAssistors.add(new SQLJoinAssistor(selectColumns, joinType, joinSql));
		return this;
	}

	public QueryAssistor configPage() {
		try {
			(SpringContextUtils.getBean(PageConvert.class)).convert(this, getHttpServletRequest());
		} catch (NoSuchBeanDefinitionException e) {
		}
		return this;
	}

	/**
	 * 获取request
	 *
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attributes == null ? null : attributes.getRequest();
	}
}
