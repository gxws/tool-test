package com.gxws.tool.test.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 具体的数据
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SpecificCache {
	/**
	 * 指定引用类
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public Class<?>referenceClass();

	/**
	 * 指定引用字段
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public String referenceField();

	/**
	 * 是否随机顺序
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return
	 * @since 1.0
	 */
	public boolean randomOrder() default false;
}
