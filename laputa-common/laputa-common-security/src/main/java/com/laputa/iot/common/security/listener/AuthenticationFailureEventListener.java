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

package com.laputa.iot.common.security.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laputa.iot.common.security.handler.AuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureProviderNotFoundEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author sommer.jiang
 * @date 2020/03/25 认证失败事件监听器
 */
public class AuthenticationFailureEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

	@Autowired(required = false)
	private AuthenticationFailureHandler failureHandler;

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
		// 此类型事件不传递处理 #2386
		if (event instanceof AuthenticationFailureProviderNotFoundEvent) {
			return;
		}

		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpServletResponse response = requestAttributes.getResponse();

		AuthenticationException authenticationException = event.getException();
		Authentication authentication = (Authentication) event.getSource();

		// 调用自定义业务实现
		if (failureHandler != null) {
			failureHandler.handle(authenticationException, authentication, request, response);
		}
	}

}
