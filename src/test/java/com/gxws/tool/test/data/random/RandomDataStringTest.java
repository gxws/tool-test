package com.gxws.tool.test.data.random;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomDataStringTest {

	@Test
	public void chars() {
		String s = null;
		RandomDataString rds = new RandomDataString();
		for (int i = 0; i < 1000000; i++) {
			s = rds.chars(true, true, true, true);
			Assert.assertEquals(s.length(), 1);
		}
	}

	@Test
	public void chinese() {
		RandomDataString rds = new RandomDataString();
		int count = 10;
		String s = null;
		for (int i = 0; i < 1000000; i++) {
			s = rds.chinese(count);
			Assert.assertEquals(s.length(), count);
		}
	}

	@Test
	public void string() {
		int count = 100;
		String s = null;
		RandomDataString rds = new RandomDataString();
		for (int i = 0; i < 1000000; i++) {
			s = rds.string(count, true, true, true, true);
			Assert.assertEquals(s.length(), count);
		}
	}
}
