package com.laputa.iot.common.log.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author sommer.jiang
 * @date 2019-05-22
 * <p>
 * 通过环境变量的形式注入 logging.file 自动维护 Spring Boot Admin Logger Viewer
 */
public class ApplicationLoggerInitializer implements EnvironmentPostProcessor, Ordered {

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		String appName = environment.getProperty("spring.application.name");
		String logBase = environment.getProperty("LOGGING_PATH", "logs");

		// spring boot admin 直接加载日志
		System.setProperty("logging.file.name", String.format("%s/%s/debug.log", logBase, appName));
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
