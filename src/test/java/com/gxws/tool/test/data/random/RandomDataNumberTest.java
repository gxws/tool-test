package com.gxws.tool.test.data.random;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomDataNumberTest {

	@Test
	public void offset() {
		RandomDataNumber rdn = new RandomDataNumber();
		String c = "123";
		String p = "100.2345";
		String n = "2.56789";
		BigDecimal bds = new BigDecimal(c).subtract(new BigDecimal(n));
		BigDecimal bde = new BigDecimal(c).add(new BigDecimal(p));
		BigDecimal r = null;
		for (int i = 0; i < 1000000; i++) {
			r = rdn.offset(c, p, n, 3);
			Assert.assertEquals(r.scale() > 3, false);
			Assert.assertEquals(bds.compareTo(r), -1);
			Assert.assertEquals(bde.compareTo(r), 1);
		}
	}

	@Test
	public void startend() {
		RandomDataNumber rdn = new RandomDataNumber();
		String start = "125.456123";
		BigDecimal bds = new BigDecimal(start);
		String end = "561.65234";
		BigDecimal bde = new BigDecimal(end);
		BigDecimal r = null;
		for (int i = 0; i < 1000000; i++) {
			r = rdn.startend(start, end, 2);
			Assert.assertEquals(r.scale() > 2, false);
			Assert.assertEquals(-1 != r.compareTo(bds), true);
			Assert.assertEquals(bde.compareTo(r), 1);
		}
	}

}
