package com.sun.szk.base.core.result;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "data", "recordsTotal" })
public class EasyUIResult<T> extends QueryResult<T> {
	public List<T> getRows() {
		return super.getData();
	}

	public long getTotal() {
		return super.getRecordsTotal();
	}
}
