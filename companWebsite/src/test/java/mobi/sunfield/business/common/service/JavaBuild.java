package mobi.sunfield.business.common.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.sun.szk.ssm.util.Java_Code_Generator;

/**
 * Created by jtwu on 2015/5/22.
 */
public class JavaBuild {
	//@Test
	public void testJavaBuild() throws IOException {
		/**
		 *
		 * @param tableName
		 *            表名
		 * @param codeName
		 *            表名对应的中文注释
		 * @param modulePakPath
		 *            模块包路径：com\\fdcz\\pro\\system
		 * @param modulePackage
		 *            模块包：com.fdcz.pro.system
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("table.name")));
		for (String tableName = reader.readLine(); tableName != null; tableName = reader.readLine()) {
			generate(tableName, "bean", "mapper", "service","contrller","mapper_xml");
		}
	}

	@Test
	public void generate() {
		generate("user", "bean", "mapper", "service","controller","mapper_xml");

	}

	private void generate(String tableName, String... templates) {
		String codeName = tableName + "管理";

		String modulePakPath = "com" + File.separator + "sun" + File.separator + "szk" + File.separator
				+ "ssm";
		String modulePackage = "com.sun.szk.ssm";
		String importPackage = modulePackage;
		Java_Code_Generator.create(tableName, codeName, modulePakPath, modulePackage, importPackage, ".", templates);
	}
}