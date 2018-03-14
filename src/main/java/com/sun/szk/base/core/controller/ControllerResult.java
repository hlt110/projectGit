package com.sun.szk.base.core.controller;

import com.sun.szk.base.javadoc.AutoJavadoc;

public class ControllerResult<R> {
	@AutoJavadoc(name = "错误码", desc = "")
	private int errorCode;
	@AutoJavadoc(name = "错误信息", desc = "")
	private String errorMessage;
	@AutoJavadoc(name = "返回结果", desc = "")
	private R result;

	public ControllerResult() {
	}

	public ControllerResult(R result) {
		this.result = result;
	}

	public ControllerResult(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public R getResult() {
		return result;
	}

	public void setResult(R result) {
		this.result = result;
	}
}
