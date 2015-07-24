package com.gxws.tool.test.data.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxws.tool.test.classtool.ClassTool;

/**
 * 随机数据生成sql文件
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class SqlFileGenerator {

	private final static Logger log = LoggerFactory.getLogger(SqlFileGenerator.class);

	private FileWriter fw;

	private File sqlFile;

	private ClassTool ct = new ClassTool();

	public SqlFileGenerator(String fileName) {
		openFile(fileName);
	}

	private void openFile(String fileName) {
		String root = this.getClass().getResource("/").getFile();
		root = root + fileName;
		sqlFile = new File(root);
		if (!sqlFile.exists()) {
			try {
				sqlFile.createNewFile();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		try {
			fw = new FileWriter(sqlFile);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void close() {
		System.out.println(sqlFile.getAbsolutePath());
		try {
			fw.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void add(Class<?> cls) {
		int numberOfTime = ct.numberOfTime(cls);
		if (0 == numberOfTime) {
			return;
		}
		String tbName = ct.tbName(cls);
		try {
			fw.write("\n\n/**\n");
			fw.write(tbName + "\n");
			fw.write("**/\n");
		} catch (IOException e1) {
			log.error(e1.getMessage(), e1);
		}
		String[] kvs = null;
		for (int i = 0; i < numberOfTime; i++) {
			kvs = ct.keyvalue(cls);
			try {
				fw.write(line(tbName, kvs[0], kvs[1]));
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				continue;
			}
		}
	}

	private String line(String tbName, String keys, String values) {
		StringBuilder sql = new StringBuilder("insert into ");
		sql.append(tbName);
		sql.append(" (");
		sql.append(keys);
		sql.append(") values (");
		sql.append(values);
		sql.append(");\n");
		return sql.toString();
	}

}
