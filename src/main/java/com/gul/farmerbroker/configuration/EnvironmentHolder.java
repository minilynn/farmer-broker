/**
 * 
 */
package com.gul.farmerbroker.configuration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 缓存Environment对象的句柄，以便在非Spring Bean对象中获取系统配置信息
 * 
 * @author Lynn
 */
@Configuration
public class EnvironmentHolder implements EnvironmentAware {
	private final static Logger log = LoggerFactory.getLogger(EnvironmentHolder.class);
	private static Environment env;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.
	 * springframework.core.env.Environment)
	 */
	@Override
	public void setEnvironment(Environment environment) {
		env = environment;
	}

	/**
	 * 获取当前应用上下文中的配置信息
	 * 
	 * @param key
	 *            环境变量的Key
	 * @return 环境变量的值，如果Key为空，则返回Null
	 */
	public static String getEnvironmentValue(String key) {
		if (StringUtils.isBlank(key)) {
			log.error("指定获取的环境变量值得环境变量Key为空，将返回Null值");
			return null;
		}
		return env.getProperty(key);
	}
}
