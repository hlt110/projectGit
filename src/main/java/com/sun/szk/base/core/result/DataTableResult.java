package com.sun.szk.base.core.result;

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
