package com.gul.farmerbroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;

import com.gul.farmerbroker.configuration.PropertiesHolder;
import com.gul.farmerbroker.goods.controller.GoodsController;

@SpringBootApplication
public class Application {
	private final static Logger logger = LoggerFactory.getLogger(GoodsController.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		// 注册监听器
		application.addListeners(e -> {
			if (e instanceof ApplicationStartedEvent) {
				PropertiesHolder.loadProperties("classpath:conf/err.properties");
			} else {
				logger.debug("Not spring start event, do nothing.");
			}
		});
		application.run(args);
	}
}
