/**
 * 
 */
package com.gul.farmerbroker.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 配置文件加载器，可加载多个配置文件。多个配置文件中的Key不能重复，否则将只保留其中一条记录。<br />
 * 通过属性名读取属性值。<br />
 * 
 * @author Lynn
 */
public class PropertiesHolder {
	private final static Map<String, String> PROP = new HashMap<>();

	/**
	 * 加载属性信息
	 * 
	 * @param propFileName
	 */
	public static void loadProperties(String propFileName) {
		try {
			Properties props = PropertiesLoaderUtils.loadAllProperties(propFileName);
			// 保存属性信息
			props.forEach((key, value) -> {
				if (PROP.containsKey(key)) {
					PROP.put((String) key, (String) value);
				}
			});
		} catch (IOException e) {
			throw new RuntimeException("Fail to load property file " + propFileName, e);
		}
	}

	/**
	 * 根据属性Key获取属性值。<br />
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(String key) {
		return PROP.get(key);
	}
}
