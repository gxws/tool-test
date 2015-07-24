package com.gxws.tool.test.classtool;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

import com.gxws.tool.common.uuid.Uuid;
import com.gxws.tool.test.data.annotation.Random;
import com.gxws.tool.test.data.annotation.RandomCache;
import com.gxws.tool.test.data.annotation.RandomDate;
import com.gxws.tool.test.data.annotation.RandomId;
import com.gxws.tool.test.data.annotation.RandomNumber;
import com.gxws.tool.test.data.annotation.RandomString;
import com.gxws.tool.test.data.annotation.SpecificCache;
import com.gxws.tool.test.data.random.RandomDataDate;
import com.gxws.tool.test.data.random.RandomDataNumber;
import com.gxws.tool.test.data.random.RandomDataString;
import com.gxws.tool.test.data.specific.SpecificDataCache;

/**
 * 处理类的注解
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class ClassTool {

	private RandomDataString rds = new RandomDataString();

	private RandomDataNumber rdn = new RandomDataNumber();

	private RandomDataDate rdd = new RandomDataDate();

	private SpecificDataCache sdc = new SpecificDataCache();

	/**
	 * 随机记录条数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param cls
	 *            orm对象类
	 * @return
	 * @since 1.0
	 */
	public int numberOfTime(Class<?> cls) {
		Random rd = cls.getAnnotation(Random.class);
		if (null == rd) {
			return 0;
		}
		return rd.numberOfTime();
	}

	/**
	 * 表名
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param cls
	 *            orm对象类
	 * @return 表名
	 * @since 1.0
	 */
	public String tbName(Class<?> cls) {
		return "`" + underline(cls.getSimpleName()) + "`";
	}

	/**
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param cls
	 *            orm对象类
	 * @return
	 * @since 1.0
	 */
	public String[] keyvalue(Class<?> cls) {
		Field[] fs = cls.getDeclaredFields();
		sdc.prePutInLine(cls);
		StringBuilder ksb = new StringBuilder("`");
		StringBuilder vsb = new StringBuilder("'");
		for (Field f : fs) {
			ksb.append(underline(f.getName()) + "`,`");
			vsb.append(value(f) + "','");
		}
		sdc.donePutInLine();
		return new String[] { ksb.substring(0, ksb.length() - 2), vsb.substring(0, vsb.length() - 2) };
	}

	/**
	 * 用下划线分隔开大小写分隔的字段名
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param uplow
	 * @return
	 * @since 1.0
	 */
	public static String underline(String uplow) {
		char[] uplowChar = uplow.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < uplowChar.length; i++) {
			if (Character.isUpperCase(uplowChar[i])) {
				sb.append("_");
				sb.append(Character.toLowerCase(uplowChar[i]));
			} else {
				sb.append(uplowChar[i]);
			}
		}
		if ("_".equals(sb.substring(0, 1))) {
			return sb.substring(1);
		} else {
			return sb.toString();
		}

	}

	/**
	 * 根据Field获取随机值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param f
	 *            字段
	 * @return 随机值
	 * @since 1.0
	 */
	private String value(Field f) {
		String value = "";
		SpecificCache s = f.getAnnotation(SpecificCache.class);
		if (null != s) {
			return specificValue(s);
		}
		RandomString rs = f.getAnnotation(RandomString.class);
		if (null != rs) {
			value = stringValue(rs);
		}
		RandomNumber rn = f.getAnnotation(RandomNumber.class);
		if (null != rn) {
			value = numberValue(rn);
		}
		RandomDate rd = f.getAnnotation(RandomDate.class);
		if (null != rd) {
			value = dateValue(rd);
		}
		RandomId ri = f.getAnnotation(RandomId.class);
		if (null != ri) {
			value = idValue();
		}
		RandomCache rc = f.getAnnotation(RandomCache.class);
		if (null != rc) {
			sdc.putInLine(f.getName(), value);
		}
		return value;
	}

	/**
	 * 获取日期型随机值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param rd
	 *            随机日期的注解对象
	 * @return 日期型随机值
	 * @since 1.0
	 */
	private String dateValue(RandomDate rd) {
		Date date = null;
		if (!"".equals(rd.start()) && !"".equals(rd.end())) {
			date = rdd.startend(rd.start(), rd.end());
		} else {
			date = rdd.offset(rd.offsetCurrent(), rd.offsetBeforeOnly(), rd.offsetAfterOnly(), rd.offsetYear(),
					rd.offsetMonth(), rd.offsetDay(), rd.offsetHour(), rd.offsetMinute(), rd.offsetSecond());
		}
		return RandomDataDate.FORMAT.format(date);
	}

	/**
	 * 获取数字型随机值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param rn
	 *            随机数字的注解对象
	 * @return 数字型随机值
	 * @since 1.0
	 */
	private String numberValue(RandomNumber rn) {
		BigDecimal result = null;
		if (!"".equals(rn.start()) && !"".equals(rn.end())) {
			result = rdn.startend(rn.start(), rn.end(), rn.decimalScale());
		} else {
			result = rdn.offset(rn.offsetCurrent(), rn.offsetPositive(), rn.offsetNegative(), rn.decimalScale());
		}
		return result.toPlainString();
	}

	/**
	 * 获取字符型随机值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param rs
	 *            随机字符的注解对象
	 * @return 字符型随机值
	 * @since 1.0
	 */
	private String stringValue(RandomString rs) {
		StringBuilder result = new StringBuilder(rs.prefix());
		if (rs.chinese()) {
			result.append(rds.chinese(rs.length()));
		} else {
			result.append(rds.string(rs.length(), rs.lowers(), rs.uppers(), rs.numbers(), rs.marks()));
		}
		result.append(rs.suffix());
		return result.toString();
	}

	/**
	 * 获取随机id值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return id值
	 * @since 1.0
	 */
	private String idValue() {
		return Uuid.order();
	}

	/**
	 * 获取指定的缓存值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param s
	 *            指定的注解
	 * @return 指定的缓存值
	 * @since 1.0
	 */
	private String specificValue(SpecificCache s) {
		return sdc.get(s.referenceClass(), s.referenceField(), s.randomOrder());
	}
	
}
