package com.sun.szk.base.core.result;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Administrator on 2015/2/6. http请求返回结果
 */

public class HttpResultEntity<R> {
	// 结果编码
	private String code;
	// 结果信息
	private String message;
	// 结果集
	private R result;

	public HttpResultEntity() {
	}

	public HttpResultEntity(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public HttpResultEntity(String code, String message, R result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	@JsonView(BaseResult.class)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonView(BaseResult.class)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonView(BaseResult.class)
	public R getResult() {
		return result;
	}

	public void setResult(R result) {
		this.result = result;
	}
}
