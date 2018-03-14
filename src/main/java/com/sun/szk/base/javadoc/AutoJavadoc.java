package com.sun.szk.base.javadoc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <ul>
 * <li>自动生成文档辅助类
 * </ul>
 *
 * @author latnok
 */
@Target({ ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoJavadoc {
	int LEN_10 = 10;
	int LEN_20 = 20;
	int LEN_40 = 40;
	int LEN_50 = 50;
	int LEN_100 = 100;
	int LEN_128 = 128;
	int LEN_256 = 256;
	int LEN_512 = 512;
	int LEN_1024 = 1024;

	/**
	 * 请求参数
	 */
	String name();

	/**
	 * 说明
	 */
	String desc();

	/**
	 * 最大长度
	 */
	int len() default 0;

	boolean required() default true;

	String version() default "1.0";
}
