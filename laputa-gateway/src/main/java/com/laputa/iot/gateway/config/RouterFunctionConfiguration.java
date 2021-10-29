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

package com.laputa.iot.gateway.config;

import com.laputa.iot.gateway.handler.ImageCodeCheckHandler;
import com.laputa.iot.gateway.handler.ImageCodeCreateHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @author sommer.jiang
 * @date 2018/7/5 路由配置信息
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

	private final ImageCodeCheckHandler imageCodeCheckHandler;

	private final ImageCodeCreateHandler imageCodeCreateHandler;

	@Bean
	public RouterFunction routerFunction() {
		return RouterFunctions
				.route(RequestPredicates.path("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
						imageCodeCreateHandler)
				.andRoute(RequestPredicates.POST("/code/check").and(RequestPredicates.accept(MediaType.ALL)),
						imageCodeCheckHandler);

	}

}
