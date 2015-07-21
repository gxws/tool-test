package com.gxws.tool.test.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 随机数字
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RandomNumber {

	/**
	 * 随机数开始范围
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public String start() default "";

	/**
	 * 随机数结束范围
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public String end() default "";

	/**
	 * 偏移量参考
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public String offSetCurrent() default "";

	/**
	 * 正向偏移量
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public String offSetPositive() default "";

	/**
	 * 反向偏移量
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public String offSetNegative() default "";
}
