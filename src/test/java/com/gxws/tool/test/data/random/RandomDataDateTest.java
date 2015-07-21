package com.gxws.tool.test.data.random;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gxws.tool.test.data.annotation.RandomDate;

public class RandomDataDateTest {

	private RandomDate ann;

	private final static String start = "2015/01/01 00:00:00";

	private final static String end = "2016/01/01 00:00:00";

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private Calendar startC;
	private Calendar endC;

	@BeforeMethod
	public void beforeMethod() throws ParseException {
		startC = Calendar.getInstance();
		startC.setTime(sdf.parse(start));
		endC = Calendar.getInstance();
		endC.setTime(sdf.parse(end));
	}

	@Test
	public void getDate() throws ParseException {
		RandomDataDate rdd = new RandomDataDate();
		for (int i = 0; i < 10000; i++) {
			Date d = rdd.getDate(ann);
			System.out.println(sdf.format(d));
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			Assert.assertEquals(startC.before(c), true);
			Assert.assertEquals(endC.after(c), true);
		}
	}

	@Test
	public void getString() {

	}
}
