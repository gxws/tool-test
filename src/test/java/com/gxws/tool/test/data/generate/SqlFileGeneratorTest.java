package com.gxws.tool.test.data.generate;

import org.testng.annotations.Test;

public class SqlFileGeneratorTest {

	@Test
	public void SqlFileGenerator() {
		SqlFileGenerator sfg = new SqlFileGenerator("sql.sql");
		sfg.add(AdTb.class);
		sfg.close();
	}
}
