package com.gxws.tool.test.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 填入随机日期的注释
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RandomDate {

	/**
	 * 随机日期开始位置<br>
	 * 格式为:yyyy/MM/dd HH:mm:ss
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 随机日期开始位置
	 * @since 1.0
	 */
	public String start() default "";

	/**
	 * 随机日期结束位置<br>
	 * 格式为:yyyy/MM/dd HH:mm:ss
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 随机日期结束位置
	 * @since 1.0
	 */
	public String end() default "";

	/**
	 * 时间参考点<br>
	 * 格式为:yyyy/MM/dd HH:mm:ss<br>
	 * 不能与start和end同时使用，如果同时存在会优先使用start和end生成随机数据。<br>
	 * 如果没有使用start和end，也没有指定时间参考点，会使用当前时间作为时间参考点。
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return 输入一段格式化日期
	 * @since 1.0
	 */
	public String offsetCurrent() default "";

	/**
	 * 只向前随机
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public boolean offsetBeforeOnly() default false;

	/**
	 * 只向后随机
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public boolean offsetAfterOnly() default false;

	/**
	 * 秒偏移量
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public int offsetSecond() default 0;

	/**
	 * 分偏移量
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public int offsetMinute() default 0;

	/**
	 * 时偏移量
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public int offsetHour() default 0;

	/**
	 * 日偏移量
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public int offsetDay() default 0;

	/**
	 * 月偏移量
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public int offsetMonth() default 0;

	/**
	 * 年偏移量
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public int offsetYear() default 0;

}
