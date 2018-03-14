package com.sun.szk.base.core.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.sun.szk.base.javadoc.AutoJavadoc;

/**
 * Created by jtwu on 2015/4/21.
 *
 */
public class BaseTimeEntity extends BaseCreateEntity {
	private static final long serialVersionUID = -7073480941325351106L;

	// 修改时间
	@AutoJavadoc(name = "修改时间", desc = "")
	private Date modifyDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
}
