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

package com.laputa.iot.org;

import com.laputa.iot.common.feign.annotation.EnableLaputaFeignClients;
import com.laputa.iot.common.security.annotation.EnableLaputaResourceServer;
import com.laputa.iot.common.swagger.annotation.EnableLaputaSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sommer.jiang
 * @date 2018年06月21日
 * <p>
 * 公司组织管理系统
 */
@EnableLaputaSwagger2
@EnableLaputaFeignClients
@EnableLaputaResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class LaputaOrgApplication {


	public static void main(String[] args) throws Exception {
		SpringApplication.run(LaputaOrgApplication.class, args);

	}




}
