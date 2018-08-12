package com.gul.farmerbroker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Lynn
 */
@Component
public class ApplicationConfiguration {
	@Value("${image.path:static/images}")
	public String IMAGE_PATH;
}
