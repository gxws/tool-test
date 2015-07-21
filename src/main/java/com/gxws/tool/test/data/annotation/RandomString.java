package com.gxws.tool.test.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 生成随机字符串
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RandomString {

	/**
	 * 随机字符串长度
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public int length() default 0;

	/**
	 * 是否包含中文
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since
	 */
	public boolean chinese() default false;

	/**
	 * 指定随机字符串具有固定前缀
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public String prefix() default "";

	/**
	 * 指定随机字符串具有固定后缀
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public String suffix() default "";

}
