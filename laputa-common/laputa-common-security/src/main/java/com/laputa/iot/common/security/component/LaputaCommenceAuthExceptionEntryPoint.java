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

package com.laputa.iot.common.security.component;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.security.util.LaputaSecurityMessageSourceUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 异常处理 {@link org.springframework.security.core.AuthenticationException } 不同细化异常处理
 *
 * @author sommer.jiang
 * @date 2020-06-14
 */
@Slf4j
@Component
@AllArgsConstructor
public class LaputaCommenceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		response.setCharacterEncoding(CommonConstants.UTF8);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		R<String> result = new R<>();
		result.setMsg(authException.getMessage());
		result.setData(authException.getMessage());
		result.setCode(CommonConstants.FAIL);

		if (authException instanceof CredentialsExpiredException
				|| authException instanceof InsufficientAuthenticationException) {
			String msg = LaputaSecurityMessageSourceUtil.getAccessor().getMessage(
					"AbstractUserDetailsAuthenticationProvider.credentialsExpired", authException.getMessage());
			result.setMsg(msg);
		}

		if (authException instanceof UsernameNotFoundException) {
			String msg = LaputaSecurityMessageSourceUtil.getAccessor().getMessage(
					"AbstractUserDetailsAuthenticationProvider.noopBindAccount", authException.getMessage());
			result.setMsg(msg);
		}

		if (authException instanceof BadCredentialsException) {
			String msg = LaputaSecurityMessageSourceUtil.getAccessor().getMessage(
					"AbstractUserDetailsAuthenticationProvider.badClientCredentials", authException.getMessage());
			result.setMsg(msg);
		}

		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}

}
