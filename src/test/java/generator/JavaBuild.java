package generator;

import generator.java.Java_Code_Generator;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;



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
		BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("generator/table.name")));
		for (String tableName = reader.readLine(); tableName != null; tableName = reader.readLine()) {
			generate(tableName, "bean", "mapper", "service","contrller","mapper_xml");
		}
	}

	@Test
	public void generate() {
		generate("sys_user", "bean", "mapper", "service","controller");

	}

	private void generate(String tableName, String... templates) {
		String codeName = tableName + "管理";

		String modulePakPath = "com" + File.separator + "sun" + File.separator + "szk" ;
		String modulePackage = "com.sun.szk";
		String importPackage = modulePackage;
		new Java_Code_Generator().create(tableName, codeName, modulePakPath, modulePackage, importPackage, ".", templates);
	}
}