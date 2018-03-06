package com.sun.szk.ssm.base.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by IntelliJ IDEA. User: jtwu Date: 12-12-5 Time: 上午10:46 Dao层异常
 */
public class DaoException extends BaseException {
	private static final long serialVersionUID = 7799029148591208607L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(StringUtils.EMPTY, cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
