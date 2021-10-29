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

package com.laputa.iot.log;

import com.laputa.iot.common.feign.annotation.EnableLaputaFeignClients;
import com.laputa.iot.common.security.annotation.EnableLaputaResourceServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author sommer.jiang
 * @date 2018年06月21日
 * <p>
 * 日志管理服务
 */

@EnableLaputaFeignClients
@EnableLaputaResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class LaputaLogApplication {


	public static void main(String[] args) throws Exception {
		SpringApplication.run(LaputaLogApplication.class, args);

	}



}
