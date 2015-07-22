package com.gxws.tool.test.data.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gxws.tool.test.classtool.RandomClass;

/**
 * 随机数据生成sql文件
 * 
 * @author zhuwl120820@gxwsxx.com
 * @since 1.0
 */
public class SqlFileGenerator {

	private Logger log = LoggerFactory.getLogger(getClass());

	private FileWriter fw;

	private File sqlFile;

	private RandomClass rc = new RandomClass();

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
		int numberOfTime = rc.numberOfTime(cls);
		String tbName = rc.tbName(cls);
		try {
			fw.write("\n\n/**\n");
			fw.write(tbName + "\n");
			fw.write("**/\n");
		} catch (IOException e1) {
			log.error(e1.getMessage(), e1);
		}
		for (int i = 0; i < numberOfTime; i++) {
			String[] kvs = rc.keyvalue(cls);
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
