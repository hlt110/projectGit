package com.sun.szk.ssm.base.result;

public class DataTableResult<T> extends QueryResult<T> {
	/**
	 * 请求次数
	 */
	private long draw;

	public long getRecordsFiltered() {
		return super.getRecordsTotal();
	}

	public long getDraw() {
		return draw;
	}
}
