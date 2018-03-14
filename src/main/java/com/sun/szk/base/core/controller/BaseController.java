package com.sun.szk.base.core.controller;

import java.util.HashMap;

import com.sun.szk.base.core.result.HttpResultEntity;

/**
 * 控制器基类
 *
 * @author JiangYingxu
 */
public class BaseController {
	public enum HttpResultEnum {
		NOT_LOGGED("-1", "未登录"), ERROR("0", "操作失败"), SUCCESS("1", "操作成功");
		private String code;
		private String message;

		HttpResultEnum(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	static {
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

	}

	/**
	 * 返回成功标示
	 *
	 * @return
	 */
	public HttpResultEntity<?> getSuccessResult() {
		return new HttpResultEntity<Object>(HttpResultEnum.SUCCESS.code, HttpResultEnum.SUCCESS.message);
	}

	/**
	 * 返回成功标示
	 *
	 * @return
	 */
	public HttpResultEntity<?> getSuccessResult(Object result) {
		return new HttpResultEntity<Object>(HttpResultEnum.SUCCESS.code, HttpResultEnum.SUCCESS.message, result);
	}

	/**
	 * 返回成功标示
	 *
	 * @return
	 */
	public static HttpResultEntity<?> getErrorResult() {
		return new HttpResultEntity<Object>(HttpResultEnum.ERROR.code, HttpResultEnum.ERROR.message);
	}

}
