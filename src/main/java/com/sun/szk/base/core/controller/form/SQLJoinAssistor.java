package com.sun.szk.base.core.controller.form;

/**
 * Created by Administrator on 2015/6/8. sql join 辅助类
 */
public class SQLJoinAssistor {
	public static enum JoinType {
		JOIN, INNER_JOIN, LEFT_OUTER_JOIN, RIGHT_OUTER_JOIN
	}

	/**
	 * 查询的列
	 */
	private String selectColumns;
	/**
	 * 链接类型
	 */
	private JoinType joinType;
	/**
	 * join sql
	 */
	private String joinSql;

	public SQLJoinAssistor() {
	}

	public SQLJoinAssistor(String selectColumns, JoinType joinType, String joinSql) {
		this.selectColumns = selectColumns;
		this.joinType = joinType;
		this.joinSql = joinSql;
	}

	public String getSelectColumns() {
		return selectColumns;
	}

	public void setSelectColumns(String selectColumns) {
		this.selectColumns = selectColumns;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public SQLJoinAssistor setJoinType(JoinType joinType) {
		this.joinType = joinType;
		return this;
	}

	public String getJoinSql() {
		return joinSql;
	}

	public SQLJoinAssistor setJoinSql(String joinSql) {
		this.joinSql = joinSql;
		return this;
	}
}
