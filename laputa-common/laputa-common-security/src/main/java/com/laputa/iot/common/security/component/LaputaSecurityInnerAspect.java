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

import cn.hutool.core.util.StrUtil;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.security.annotation.Inner;
import com.laputa.iot.common.security.util.LaputaSecurityMessageSourceUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sommer.jiang
 * @date 2020/11/26
 * <p>
 * 服务间接口不鉴权处理逻辑
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class LaputaSecurityInnerAspect {

	private final HttpServletRequest request;

	@SneakyThrows
	@Around("@within(inner) || @annotation(inner)")
	public Object around(ProceedingJoinPoint point, Inner inner) {
		// 先判断 inner 是否为空, 为空则获取类上注解
		if (inner == null) {
			Class<?> aClass = point.getTarget().getClass();
			inner = AnnotationUtils.findAnnotation(aClass, Inner.class);
		}

		String header = request.getHeader(SecurityConstants.FROM);
		if (inner.value() && !StrUtil.equals(SecurityConstants.FROM_IN, header)) {
			log.warn("访问接口 {} 没有权限", inner.value());
			throw new AccessDeniedException(LaputaSecurityMessageSourceUtil.getAccessor().getMessage(
					"AbstractAccessDecisionManager.accessDenied", new Object[] { inner.value() }, "access denied"));
		}
		return point.proceed();
	}

}
