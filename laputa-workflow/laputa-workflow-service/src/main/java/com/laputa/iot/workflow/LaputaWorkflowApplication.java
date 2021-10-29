package com.laputa.iot.workflow;


import com.laputa.iot.common.feign.annotation.EnableLaputaFeignClients;
import com.laputa.iot.common.security.annotation.EnableLaputaResourceServer;
import com.laputa.iot.common.swagger.annotation.EnableLaputaSwagger2;
import com.laputa.iot.workflow.config.DatabaseAutoConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;


/**
 * @author sommer.jiang
 * @date 2018年06月21日
 * <p>
 * 工作流管理系统
 */
@EnableLaputaSwagger2
@EnableLaputaFeignClients
@EnableLaputaResourceServer
@EnableDiscoveryClient
@SpringBootApplication
@Import(value={DatabaseAutoConfiguration.class})
public class LaputaWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaputaWorkflowApplication.class, args);
    }

}
