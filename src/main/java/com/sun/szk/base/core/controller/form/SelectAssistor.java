package com.sun.szk.base.core.controller.form;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SelectAssistor {
	private List<String> aliasColumnList;
	// sql join 辅助类
	private LinkedList<SQLJoinAssistor> sqlJoinAssistorList;
	// sql 运算符辅助类
	public List<OperatorAssistor> operatorAssistorList;
	// 是否使用distinct
	public Boolean distinct = false;
	// 扩展的whereSql
	public List<String> whereSqlList;

	public SelectAssistor addAliasColumn(String aliasColumn) {
		if (aliasColumnList == null) {
			aliasColumnList = new ArrayList<>();
		}
		aliasColumnList.add(aliasColumn);
		return this;
	}

	public List<String> getAliasColumnList() {
		return aliasColumnList;
	}

	public SelectAssistor leftJoinAssistor(String selectColumns, String joinSql) {
		return addJoinAssistors(selectColumns, SQLJoinAssistor.JoinType.LEFT_OUTER_JOIN, joinSql);
	}

	public SelectAssistor rightJoinAssistor(String selectColumns, String joinSql) {
		return addJoinAssistors(selectColumns, SQLJoinAssistor.JoinType.RIGHT_OUTER_JOIN, joinSql);
	}

	public SelectAssistor innerJoinAssistor(String selectColumns, String joinSql) {
		return addJoinAssistors(selectColumns, SQLJoinAssistor.JoinType.INNER_JOIN, joinSql);
	}

	public SelectAssistor outerJoinAssistor(String selectColumns, String joinSql) {
		return addJoinAssistors(selectColumns, SQLJoinAssistor.JoinType.JOIN, joinSql);
	}

	private SelectAssistor addJoinAssistors(String selectColumns, SQLJoinAssistor.JoinType joinType, String joinSql) {
		if (sqlJoinAssistorList == null) {
			sqlJoinAssistorList = new LinkedList<>();
		}
		sqlJoinAssistorList.add(new SQLJoinAssistor(selectColumns, joinType, joinSql));
		return this;
	}

	public LinkedList<SQLJoinAssistor> getSqlJoinAssistorList() {
		return sqlJoinAssistorList;
	}

	public List<String> getWhereSqlList() {
		return whereSqlList;
	}

	public SelectAssistor likeOperator(String column) {
		return addOperatorAssistor(column, OperatorAssistor.operatorType.LIKE);
	}

	public SelectAssistor lessThenOperator(String column) {
		return addOperatorAssistor(column, OperatorAssistor.operatorType.LESS_THEN);
	}

	public SelectAssistor moreThenOperator(String column) {
		return addOperatorAssistor(column, OperatorAssistor.operatorType.MORE_THEN);
	}

	public SelectAssistor lessEqualsOperator(String column) {
		return addOperatorAssistor(column, OperatorAssistor.operatorType.LESS_EQUAL);
	}

	public SelectAssistor moreEqualsOperator(String column) {
		return addOperatorAssistor(column, OperatorAssistor.operatorType.MORE_EQUAL);
	}

	public SelectAssistor inOperator(String column, Object... values) {
		return addOperatorAssistor(column, OperatorAssistor.operatorType.IN, values);
	}

	private SelectAssistor addOperatorAssistor(String column, OperatorAssistor.operatorType operatorType,
			Object... values) {
		if (StringUtils.isEmpty(column) || null == operatorType) {
			return this;
		}
		if (operatorAssistorList == null) {
			operatorAssistorList = new ArrayList<>();
		}
		operatorAssistorList.add(new OperatorAssistor(column, operatorType, values));
		return this;
	}

	public List<OperatorAssistor> getOperatorAssistorList() {
		return operatorAssistorList;
	}

	public SelectAssistor addWhereSql(String whereSql) {
		if (whereSqlList == null) {
			whereSqlList = new ArrayList<>();
		}
		whereSqlList.add(whereSql);
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

	public SelectAssistor addDistinct() {
		distinct = true;
		return this;
	}
}
