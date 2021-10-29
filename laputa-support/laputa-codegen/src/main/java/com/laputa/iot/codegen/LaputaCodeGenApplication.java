/*
 *    Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the www.laputa-iot.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: SommerJiang (sommer_jiang@163.com)
 */

package com.laputa.iot.codegen;

import com.laputa.iot.common.datasource.annotation.EnableDynamicDataSource;
import com.laputa.iot.common.feign.annotation.EnableLaputaFeignClients;
import com.laputa.iot.common.security.annotation.EnableLaputaResourceServer;
import com.laputa.iot.common.swagger.annotation.EnableLaputaSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sommer.jiang
 * @date 2018/07/29 代码生成模块
 */
@EnableDynamicDataSource
@EnableLaputaSwagger2
@EnableLaputaFeignClients
@EnableDiscoveryClient
@EnableLaputaResourceServer
@SpringBootApplication
public class LaputaCodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaputaCodeGenApplication.class, args);
	}

}
