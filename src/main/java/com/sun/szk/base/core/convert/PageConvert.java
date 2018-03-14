package com.sun.szk.base.core.convert;

import javax.servlet.http.HttpServletRequest;

import com.sun.szk.base.core.controller.form.QueryAssistor;

/**
 * Created by Administrator on 2015/7/21. 翻页信息转换器，前台框架的分页标示不统一，在此做适配
 */
public interface PageConvert {
	public void convert(QueryAssistor queryAssistor, HttpServletRequest request);
}
