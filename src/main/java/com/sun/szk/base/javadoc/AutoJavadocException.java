package com.sun.szk.base.javadoc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <ul>
 * <li>自动生成文档辅助异常类
 * </ul>
 *
 * @author latnok
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoJavadocException {
	AutoJavadoc[]value();
}
