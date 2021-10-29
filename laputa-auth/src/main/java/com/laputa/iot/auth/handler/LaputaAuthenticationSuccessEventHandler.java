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


import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.core.util.KeyStrResolver;
import com.laputa.iot.common.core.util.WebUtils;
import com.laputa.iot.common.log.util.SysLogUtils;
import com.laputa.iot.common.security.handler.AuthenticationSuccessHandler;
import com.laputa.iot.log.api.dto.SysLogDTO;
import com.laputa.iot.log.api.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sommer.jiang
 * @date 2018/10/8
 */
@Slf4j
@Component
@AllArgsConstructor
public class LaputaAuthenticationSuccessEventHandler implements AuthenticationSuccessHandler {

	private final RemoteLogService logService;

	private final KeyStrResolver tenantKeyStrResolver;

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 * @param request 请求
	 * @param response 返回
	 */
	@Async
	@Override
	public void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		String username = authentication.getName();
		SysLogDTO sysLog = SysLogUtils.getSysLog(request, username);
		sysLog.setTitle(username + "用户登录");
		sysLog.setParams(username);
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		sysLog.setServiceId(WebUtils.extractClientId(header).orElse("N/A"));
		sysLog.setTenantId(Long.parseLong(tenantKeyStrResolver.key()));

		logService.saveLog(sysLog, SecurityConstants.FROM_IN);
		log.info("用户：{} 登录成功", username);
	}

}
