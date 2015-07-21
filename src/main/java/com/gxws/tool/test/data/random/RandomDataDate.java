package com.gxws.tool.test.data.random;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gxws.tool.test.data.annotation.RandomDate;

/**
 * 生成随机日期
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class RandomDataDate {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * 获取Date类型的随机日期
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return Date类型的随机日期
	 * @since 1.0
	 */
	public Date getDate(RandomDate ann) {
		if ("" != ann.start() && "" != ann.end()) {
			try {
				return startend(ann.start(), ann.end());
			} catch (ParseException e) {
				return new Date();
			}
		} else {
			try {
				return offset(ann.offsetCurrent(), ann.offsetBeforeOnly(),
						ann.offsetAfterOnly(), ann.offsetYear(),
						ann.offsetMonth(), ann.offsetDay(), ann.offsetHour(),
						ann.offsetMinute(), ann.offsetSecond());
			} catch (ParseException e) {
				return new Date();
			}
		}
	}

	/**
	 * 获取String格式的随机日期
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @return String格式的随机日期
	 * @since 1.0
	 */
	public String getString(RandomDate ann) {
		return sdf.format(getDate(ann));
	}

	/**
	 * start和end方式获取随机日期
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param start
	 *            开始日期
	 * @param end
	 *            截止日期
	 * @return 随机日期
	 * @throws ParseException
	 *             日期格式错误
	 * @since 1.0
	 */
	private Date startend(String start, String end) throws ParseException {
		Date startDate = sdf.parse(start);
		Date endDate = sdf.parse(end);
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
	 * @throws ParseException
	 *             日期格式错误
	 * @since 1.0
	 */
	private Date offset(String current, boolean beforeOnly, boolean afterOnly,
			int... offset) throws ParseException {
		Date now = null;
		if (null == current || "".equals(current)) {
			now = new Date();
		} else {
			now = sdf.parse(current);
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

	private Date randomDate(Date start, Date end) {
		long b = start.getTime();
		long m = end.getTime() - b;
		String r = randomBase("", String.valueOf(m));
		BigInteger bdr = new BigInteger(r);
		BigInteger bdm = new BigInteger(String.valueOf(m));
		m = bdr.mod(bdm).longValue();
		return new Date(b + m);
	}

	private String randomBase(String baseLong, String value) {
		BigDecimal bd = BigDecimal.valueOf(Math.random());
		String r = bd.toString().split("\\.")[1];
		r = baseLong + r;
		if (r.length() > value.length()) {
			return r;
		} else {
			return randomBase(r, value);
		}
	}
}
