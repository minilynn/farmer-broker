/**
 * 
 */
package com.gul.farmerbroker.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring上下文对象管理器
 * 
 * @author Lynn
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext context) {
		this.applicationContext = context;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> clz) {
		return (T) applicationContext.getBean(clz);
	}
}
