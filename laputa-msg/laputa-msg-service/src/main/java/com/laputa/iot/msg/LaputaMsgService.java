/*
 *
 *      Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the www.laputa-iot.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: SommerJiang (sommer_jiang@163.com)
 *
 */

package com.laputa.iot.msg;

import com.laputa.iot.common.feign.annotation.EnableLaputaFeignClients;
import com.laputa.iot.common.security.annotation.EnableLaputaResourceServer;
import com.laputa.iot.common.swagger.annotation.EnableLaputaSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author sommer.jiang
 * @date 2021年06月21日
 * <p>
 * 消息管理中心
 */
@EnableLaputaSwagger2
@EnableLaputaFeignClients
@EnableLaputaResourceServer
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = {"com.laputa.iot.msg","com.laputa.iot.common.data"})
public class LaputaMsgService {


	public static void main(String[] args) {
		SpringApplication.run(LaputaMsgService.class, args);

	}

}
