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

package com.laputa.iot.auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.core.util.KeyStrResolver;
import com.laputa.iot.common.core.util.WebUtils;
import com.laputa.iot.common.log.util.LogTypeEnum;
import com.laputa.iot.common.log.util.SysLogUtils;
import com.laputa.iot.common.security.handler.AuthenticationFailureHandler;
import com.laputa.iot.log.api.dto.SysLogDTO;
import com.laputa.iot.log.api.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author sommer.jiang
 * @date 2018/10/8
 */
@Slf4j
@Component
@AllArgsConstructor
public class LaputaAuthenticationFailureEventHandler implements AuthenticationFailureHandler {

	private final RemoteLogService logService;

	private final KeyStrResolver tenantKeyStrResolver;

	/**
	 * 异步处理，登录失败方法
	 * <p>
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication 登录的authenticationException 对象
	 * @param request 请求
	 * @param response 响应
	 */
	@Async
	@Override
	@SneakyThrows
	public void handle(AuthenticationException authenticationException, Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) {
		String username = authentication.getName();
		SysLogDTO sysLog = SysLogUtils.getSysLog(request, username);
		sysLog.setTitle(username + "用户登录");
		sysLog.setType(LogTypeEnum.ERROR.getType());
		sysLog.setParams(username);
		sysLog.setException(authenticationException.getLocalizedMessage());
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		sysLog.setServiceId(WebUtils.extractClientId(header).orElse("N/A"));
		sysLog.setTenantId(Long.parseLong(tenantKeyStrResolver.key()));

		logService.saveLog(sysLog, SecurityConstants.FROM_IN);

		log.info("用户：{} 登录失败，异常：{}", username, authenticationException.getLocalizedMessage());
	}

}
