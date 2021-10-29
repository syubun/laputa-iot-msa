package com.laputa.iot.daemon.quartz;

import com.laputa.iot.common.feign.annotation.EnableLaputaFeignClients;
import com.laputa.iot.common.security.annotation.EnableLaputaResourceServer;
import com.laputa.iot.common.swagger.annotation.EnableLaputaSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sommer.jiang
 * @date 2019/01/23 定时任务模块
 */
@EnableLaputaSwagger2
@EnableLaputaFeignClients
@EnableLaputaResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class LaputaDaemonJob {

	public static void main(String[] args) {
		SpringApplication.run(LaputaDaemonJob.class, args);
	}

}
