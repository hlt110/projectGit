package com.sun.szk.base.core.convert.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.sun.szk.base.core.controller.form.QueryAssistor;
import com.sun.szk.base.core.convert.PageConvert;

/**
 * Created by Administrator on 2015/7/21.
 */
public class DataTablePageConvertImpl implements PageConvert {
	public void convert(QueryAssistor queryAssistor, HttpServletRequest request) {
		String length = request.getParameter("length");
		if (StringUtils.isNotEmpty(length)) {
			queryAssistor.setRows(Integer.valueOf(length));
		}
		String start = request.getParameter("start");
		if (StringUtils.isNotEmpty(start)) {
			queryAssistor.setPage(Integer.valueOf(start) / queryAssistor.getRows() + 1);
		}
	}
}
