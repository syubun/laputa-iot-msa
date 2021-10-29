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

import java.util.Locale;

import com.laputa.iot.common.security.exception.ForbiddenException;
import com.laputa.iot.common.security.exception.InvalidException;
import com.laputa.iot.common.security.exception.MethodNotAllowedException;
import com.laputa.iot.common.security.exception.LaputaAuth2Exception;
import com.laputa.iot.common.security.exception.ServerErrorException;
import com.laputa.iot.common.security.exception.UnauthorizedException;
import com.laputa.iot.common.security.util.LaputaSecurityMessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * @author sommer.jiang
 * @date 2020-06-20 OAutH Server 异常处理,重写oauth 默认实现
 */
@Slf4j
public class LaputaWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) {

		// Try to extract a SpringSecurityException from the stacktrace
		Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

		Exception ase = (AuthenticationException) throwableAnalyzer
				.getFirstThrowableOfType(AuthenticationException.class, causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
		}

		ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class,
				causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
		}

		ase = (InvalidGrantException) throwableAnalyzer.getFirstThrowableOfType(InvalidGrantException.class,
				causeChain);
		if (ase != null) {
			String msg = LaputaSecurityMessageSourceUtil.getAccessor().getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials", ase.getMessage(), Locale.CHINA);
			return handleOAuth2Exception(new InvalidException(msg, ase));
		}

		ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer
				.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new MethodNotAllowedException(ase.getMessage(), ase));
		}

		ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

		if (ase != null) {
			return handleOAuth2Exception((OAuth2Exception) ase);
		}

		return handleOAuth2Exception(new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));

	}

	private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {
		int status = e.getHttpErrorCode();
		// 客户端异常直接返回客户端,不然无法解析
		if (e instanceof ClientAuthenticationException) {
			return new ResponseEntity<>(e, HttpStatus.valueOf(status));
		}
		return new ResponseEntity<>(new LaputaAuth2Exception(e.getMessage(), e.getOAuth2ErrorCode()),
				HttpStatus.valueOf(status));

	}

}
