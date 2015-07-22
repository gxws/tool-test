package com.gxws.tool.test.data.random;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 生成随机日期
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class RandomDataDate {

	public static final SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	/**
	 * start和end方式获取随机日期
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param start
	 *            开始日期
	 * @param end
	 *            截止日期
	 * @return 随机日期
	 * @since 1.0
	 */
	public Date startend(String start, String end) {
		Date startDate;
		try {
			startDate = FORMAT.parse(start);
		} catch (ParseException e) {
			startDate = new Date();
		}
		Date endDate;
		try {
			endDate = FORMAT.parse(end);
		} catch (ParseException e) {
			endDate = new Date();
		}
		return randomDate(startDate, endDate);
	}

	/**
	 * offset方式获取随机日期
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param current
	 *            当前日期
	 * @param beforeOnly
	 *            是否只向前
	 * @param afterOnly
	 *            是否只向后
	 * @param offset
	 *            随机日期相对当前日期的偏移量
	 * @return 随机日期
	 * @since 1.0
	 */
	public Date offset(String current, boolean beforeOnly, boolean afterOnly,
			int... offset) {
		Date now = null;
		if (null == current || "".equals(current)) {
			now = new Date();
		} else {
			try {
				now = FORMAT.parse(current);
			} catch (ParseException e) {
				now = new Date();
			}
		}
		if (beforeOnly && afterOnly) {
			return now;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		Calendar start = (Calendar) c.clone();
		Calendar end = (Calendar) c.clone();
		if (!afterOnly) {
			start.add(Calendar.YEAR, -offset[0]);
			start.add(Calendar.MONTH, -offset[1]);
			start.add(Calendar.DATE, -offset[2]);
			start.add(Calendar.HOUR, -offset[3]);
			start.add(Calendar.MINUTE, -offset[4]);
			start.add(Calendar.SECOND, -offset[5]);
		}
		if (!beforeOnly) {
			end.add(Calendar.YEAR, offset[0]);
			end.add(Calendar.MONTH, offset[1]);
			end.add(Calendar.DATE, offset[2]);
			end.add(Calendar.HOUR, offset[3]);
			end.add(Calendar.MINUTE, offset[4]);
			end.add(Calendar.SECOND, offset[5]);
		}
		return randomDate(start.getTime(), end.getTime());
	}

	/**
	 * 生成随机日期
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param start
	 *            开始边界
	 * @param end
	 *            截止边界
	 * @return 随机日期
	 * @since 1.0
	 */
	private Date randomDate(Date start, Date end) {
		BigDecimal bis = BigDecimal.valueOf(start.getTime());
		BigDecimal bim = BigDecimal.valueOf(end.getTime()).subtract(bis);
		if (0 == bim.compareTo(BigDecimal.ZERO)) {
			return new Date();
		}
		BigDecimal bdr = new BigDecimal(Math.random());
		bdr = bim.multiply(bdr).add(bis);
		return new Date(bdr.longValue());
	}

}
