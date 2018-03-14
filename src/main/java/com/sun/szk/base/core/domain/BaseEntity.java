package com.sun.szk.base.core.domain;

import com.sun.szk.base.javadoc.AutoJavadoc;

/**
 * Created by jtwu on 2015/4/21.
 *
 */
public class BaseEntity extends BaseTimeEntity {
	private static final long serialVersionUID = 6468926052770326495L;

	// 操作人
	@AutoJavadoc(name = "操作人Id", desc = "")
	private String operatorId;
	// 操作人Id
	@AutoJavadoc(name = "操作人名称", desc = "")
	private String operatorName;

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}
