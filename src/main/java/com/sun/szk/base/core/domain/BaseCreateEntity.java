package com.sun.szk.base.core.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.sun.szk.base.javadoc.AutoJavadoc;

/**
 * Created by jtwu on 2015/4/21.
 *
 */
public class BaseCreateEntity implements Serializable {
	private static final long serialVersionUID = 3413405545232687842L;

	// 创建时间
	@AutoJavadoc(name = "创建时间", desc = "")
	private Date createDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateDateTime() {
		return createDateTime;
	}

	public BaseCreateEntity setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
		return this;
	}
}
