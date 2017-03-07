/*
 * configUtil.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件的信息读取
 * @author LT
 * @version 1.0, 2015年9月10日
 */
public class ConfigUtil {
	public static Properties props = new Properties();
	static{
		ClassLoader loader = ConfigUtil.class.getClassLoader();
		InputStream in = loader.getResourceAsStream("util/daoconfig.properies");
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getInstance(String key){
		return props.getProperty(key);
	}
}
