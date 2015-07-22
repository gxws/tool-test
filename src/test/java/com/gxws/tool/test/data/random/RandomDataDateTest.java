package com.gxws.tool.test.data.random;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomDataDateTest {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Test
	public void offset() throws ParseException {
		RandomDataDate rdd = new RandomDataDate();
		for (int i = 0; i < 10; i++) {
			Date d = rdd.offset("", true, true, new int[6]);
			Assert.assertEquals(sdf.format(d), sdf.format(new Date()));
		}
		for (int i = 0; i < 10; i++) {
			Date d = rdd.offset("", false, false, new int[6]);
			Assert.assertEquals(sdf.format(d), sdf.format(new Date()));
		}
		String start = "2012/11/29 22:58:59";
		String end = "2015/02/02 01:01:01";
		Calendar startC = Calendar.getInstance();
		startC.setTime(sdf.parse(start));
		Calendar endC = Calendar.getInstance();
		endC.setTime(sdf.parse(end));
		for (int i = 0; i < 1000000; i++) {
			Date d = rdd.offset("2014/01/01 00:00:00", false, false, new int[] {
					1, 1, 1, 1, 1, 1 });
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			Assert.assertEquals(startC.before(c), true);
			Assert.assertEquals(endC.after(c), true);
		}
	}

	@Test
	public void startend() throws ParseException {
		String start = "2015/01/01 00:00:00";
		String end = "2016/01/01 00:00:00";
		Calendar startC = Calendar.getInstance();
		startC.setTime(sdf.parse(start));
		Calendar endC = Calendar.getInstance();
		endC.setTime(sdf.parse(end));
		RandomDataDate rdd = new RandomDataDate();
		for (int i = 0; i < 1000000; i++) {
			Date d = rdd.startend(start, end);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			Assert.assertEquals(startC.before(c), true);
			Assert.assertEquals(endC.after(c), true);
		}
	}

}
