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
	 * 是否包含小写字母
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public boolean lowers() default true;

	/**
	 * 是否包含大写字母
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public boolean uppers() default false;

	/**
	 * 是否包含数字
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public boolean numbers() default false;

	/**
	 * 是否包含特殊字符
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public boolean marks() default false;

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
