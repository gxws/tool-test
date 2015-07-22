package com.gxws.tool.test.data.random;

import java.math.BigDecimal;

/**
 * 生成随机数字
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class RandomDataNumber {

	/**
	 * 指定开始和结束范围生成随机数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param start
	 *            开始
	 * @param end
	 *            结束
	 * @param decimalScale
	 *            保留小数位
	 * @return 随机数值
	 * @since 1.0
	 */
	public BigDecimal startend(String start, String end, int decimalScale) {
		return randomNumber(new BigDecimal(start), new BigDecimal(end),
				decimalScale);
	}

	/**
	 * 根据偏移量生成随机数
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param current
	 *            参考值
	 * @param positive
	 *            正向偏移量
	 * @param negative
	 *            反向偏移量
	 * @param decimalScale
	 *            保留小数位
	 * @return 随机数值
	 * @since 1.0
	 */
	public BigDecimal offset(String current, String positive, String negative,
			int decimalScale) {
		BigDecimal bdc = new BigDecimal(current);
		BigDecimal start = bdc.subtract(new BigDecimal(negative));
		BigDecimal end = bdc.add(new BigDecimal(positive));
		return randomNumber(start, end, decimalScale);
	}

	/**
	 * 生成随机数值
	 * 
	 * @author zhuwl120820@gxwsxx.com
	 * @param start
	 *            开始
	 * @param end
	 *            截止
	 * @param decimalScale
	 *            保留小数位
	 * @return 随机数值
	 * @since 1.0
	 */
	private BigDecimal randomNumber(BigDecimal start, BigDecimal end,
			int decimalScale) {
		BigDecimal bdm = end.subtract(start);
		if (1 != bdm.compareTo(BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		}
		BigDecimal bdr = new BigDecimal(Math.random());
		bdr = bdr.multiply(bdm).add(start);
		BigDecimal result = bdr.setScale(decimalScale, BigDecimal.ROUND_DOWN);
		if (-1 == result.compareTo(start)) {
			result = bdr.setScale(decimalScale, BigDecimal.ROUND_UP);
		}
		if (1 == result.compareTo(end)) {
			result = bdr.setScale(decimalScale, BigDecimal.ROUND_DOWN);
		}
		return result;
	}
}
