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

package com.laputa.iot.auth.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.laputa.iot.auth.service.LaputaClientDetailsServiceImpl;
import com.laputa.iot.auth.service.LaputaCustomeTokenServices;
import com.laputa.iot.common.security.component.LaputaCommenceAuthExceptionEntryPoint;
import com.laputa.iot.common.security.component.LaputaWebResponseExceptionTranslator;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import java.util.Arrays;

/**
 * @author sommer.jiang
 * @date 2018/6/22 认证服务器配置
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
@ConditionalOnProperty(prefix = "laputa.auth", name = "token", havingValue = "redis", matchIfMissing = true)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final ClientDetailsService laputaClientDetailsServiceImpl;

	private final AuthenticationManager authenticationManagerBean;

	private final UserDetailsService laputaUserDetailsService;

	private final AuthorizationCodeServices authorizationCodeServices;

	private final TokenStore redisTokenStore;

	private final TokenEnhancer tokenEnhancer;

	private final ObjectMapper objectMapper;

	private final LaputaClientDetailsServiceImpl laputaClientDetailsService;

	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		clients.withClientDetails(laputaClientDetailsServiceImpl);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.allowFormAuthenticationForClients()
				.authenticationEntryPoint(new LaputaCommenceAuthExceptionEntryPoint(objectMapper))
				.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//				.tokenStore(redisTokenStore)
//				.tokenEnhancer(tokenEnhancer)
//				.userDetailsService(laputaUserDetailsService)
//				.authorizationCodeServices(authorizationCodeServices).authenticationManager(authenticationManagerBean)
//				.reuseRefreshTokens(false).pathMapping("/oauth/confirm_access", "/token/confirm_access")
//				.exceptionTranslator(new LaputaWebResponseExceptionTranslator());

		// 登录踢出之前登录用户
		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.tokenServices(tokenServices())
				.authorizationCodeServices(authorizationCodeServices)
				.authenticationManager(authenticationManagerBean)
				.reuseRefreshTokens(false)
				.pathMapping("/oauth/confirm_access", "/token/confirm_access")
				.exceptionTranslator(new LaputaWebResponseExceptionTranslator());
	}



	@Bean
	public LaputaCustomeTokenServices tokenServices() {
		LaputaCustomeTokenServices tokenServices = new LaputaCustomeTokenServices();
		tokenServices.setTokenStore(redisTokenStore);
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setReuseRefreshToken(false);
		tokenServices.setClientDetailsService(laputaClientDetailsService);
		tokenServices.setTokenEnhancer(tokenEnhancer);
		addUserDetailsService(tokenServices, laputaUserDetailsService);
		return tokenServices;
	}


	private void addUserDetailsService(LaputaCustomeTokenServices tokenServices, UserDetailsService userDetailsService) {
		if (userDetailsService != null) {
			PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
			provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(
					userDetailsService));
			tokenServices.setAuthenticationManager(new ProviderManager(Arrays.<AuthenticationProvider> asList(provider)));
		}
	}

//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.accessTokenConverter(accessTokenConverter());
//		endpoints.tokenStore(redisTokenStore).authenticationManager(authenticationManagerBean);
//	}


}
