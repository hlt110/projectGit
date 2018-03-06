package com.sun.szk.ssm.base.result;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({ "data", "recordsTotal" })
public class EasyUIResult<T> extends QueryResult<T> {
	public List<T> getRows() {
		return super.getData();
	}

	public long getTotal() {
		return super.getRecordsTotal();
	}
}
