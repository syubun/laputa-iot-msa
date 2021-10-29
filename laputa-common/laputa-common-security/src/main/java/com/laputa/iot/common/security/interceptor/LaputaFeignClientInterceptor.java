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

package com.laputa.iot.common.security.interceptor;

import cn.hutool.core.collection.CollUtil;
import com.laputa.iot.common.core.constant.SecurityConstants;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.commons.security.AccessTokenContextRelay;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.Collection;

/**
 * @author sommer.jiang
 * @date 2020/8/13 扩展OAuth2FeignRequestInterceptor
 */
@Slf4j
public class LaputaFeignClientInterceptor extends OAuth2FeignRequestInterceptor {

	private final OAuth2ClientContext oAuth2ClientContext;

	private final AccessTokenContextRelay accessTokenContextRelay;

	/**
	 * Default constructor which uses the provided OAuth2ClientContext and Bearer tokens
	 * within Authorization header
	 * @param oAuth2ClientContext provided context
	 * @param resource type of resource to be accessed
	 * @param accessTokenContextRelay
	 */
	public LaputaFeignClientInterceptor(OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails resource,
										AccessTokenContextRelay accessTokenContextRelay) {
		super(oAuth2ClientContext, resource);
		this.oAuth2ClientContext = oAuth2ClientContext;
		this.accessTokenContextRelay = accessTokenContextRelay;
	}

	/**
	 * Create a template with the header of provided name and extracted extract 1. 如果使用
	 * 非web 请求，header 区别 2. 根据authentication 还原请求token
	 * @param template
	 */
	@Override
	public void apply(RequestTemplate template) {
		Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
		if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN)) {
			return;
		}

		accessTokenContextRelay.copyToken();
		if (oAuth2ClientContext != null && oAuth2ClientContext.getAccessToken() != null) {
			super.apply(template);
		}
	}

}
