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

package com.laputa.iot.common.log.util;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;

import com.laputa.iot.log.api.dto.SysLogDTO;
import lombok.experimental.UtilityClass;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 系统日志工具类
 *
 * @author Sommer.Jiang
 */
@UtilityClass
public class SysLogUtils {

	public SysLogDTO getSysLog() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLogDTO sysLog = new SysLogDTO();
		sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
		sysLog.setType(LogTypeEnum.NORMAL.getType());
		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		sysLog.setServiceId(getClientId());
		return sysLog;
	}

	public SysLogDTO getSysLog(HttpServletRequest request, String username) {
		SysLogDTO sysLog = new SysLogDTO();
		sysLog.setCreateBy(username);
		sysLog.setType(LogTypeEnum.NORMAL.getType());
		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		sysLog.setServiceId(getClientId());
		return sysLog;
	}

	/**
	 * 获取客户端
	 * @return clientId
	 */
	private String getClientId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
			return auth2Authentication.getOAuth2Request().getClientId();
		}
		return null;
	}

	/**
	 * 获取用户名称
	 * @return username
	 */
	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return authentication.getName();
	}

}
