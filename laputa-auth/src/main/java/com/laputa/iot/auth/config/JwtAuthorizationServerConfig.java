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

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laputa.iot.auth.service.LaputaClientDetailsServiceImpl;
import com.laputa.iot.auth.service.LaputaCustomeTokenServices;
import com.laputa.iot.common.security.component.LaputaCommenceAuthExceptionEntryPoint;
import com.laputa.iot.common.security.component.LaputaWebResponseExceptionTranslator;
import com.laputa.iot.common.security.service.LaputaUser;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sommer.jiang
 * @date 2018/6/22 认证服务器配置
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
@ConditionalOnProperty(prefix = "laputa.auth", name = "token", havingValue = "jwt", matchIfMissing = false)
public class JwtAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final ClientDetailsService laputaClientDetailsServiceImpl;

	private final AuthenticationManager authenticationManagerBean;


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

	// JWT
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new FileSystemResource(System.getProperty("user.dir") + "/certificate/lion-jwt.jks"), "123456".toCharArray());
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
			/***
			 * 重写增强token方法,用于自定义一些token总需要封装的信息
			 */
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				LaputaUser baseUser = ((LaputaUser) authentication.getPrincipal());
				// 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
				final Map<String, Object> additionalInformation = new HashMap<>();
				additionalInformation.put("user_info", JSON.toJSONString(baseUser));
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
				return enhancedToken;
			}
		};
		// 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
//		accessTokenConverter.setSigningKey("SigningKey");
		accessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("lion-jwt"));
		return accessTokenConverter;
	}

	@Bean
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(new JwtTokenStore(accessTokenConverter()));
		/**
		 * jwt 无状态方式
		 */
		tokenServices.setTokenEnhancer(accessTokenConverter());
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setClientDetailsService(laputaClientDetailsService);
		// 设置access_token有效时长12小时，默认12小时
		tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
		// 设置refresh_token有效时长7天，默认30天
		tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
		tokenServices.setTokenEnhancer(tokenEnhancer);
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

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.accessTokenConverter(accessTokenConverter());
		endpoints.tokenStore(redisTokenStore).authenticationManager(authenticationManagerBean);
	}




}
//@Configuration
//@AllArgsConstructor
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//	@Bean
//	public TokenStore tokenStore() {
//		/**
//		 * redis 存储有状态方式
//		 */
////        return new RedisTokenStore(redisConnectionFactory);
//		/**
//		 * jwt 无状态方式
//		 */
//		return new JwtTokenStore(jwtAccessTokenConverter());
//	}

//	// 资源ID
//	private static final String SOURCE_ID = "order";
//	private static final int ACCESS_TOKEN_TIMER = 60 * 60 * 24;
//	private static final int REFRESH_TOKEN_TIMER = 60 * 60 * 24 * 30;
//
//	private final TokenStore redisTokenStore;
//
//	private final ClientDetailsService laputaClientDetailsServiceImpl;
//
//	@Autowired
//	AuthenticationManager authenticationManager;
//
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.withClientDetails(laputaClientDetailsServiceImpl);
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.accessTokenConverter(accessTokenConverter());
//		endpoints.tokenStore(redisTokenStore).authenticationManager(authenticationManager);
//	}
//
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//		// 允许表单认证
//		oauthServer.allowFormAuthenticationForClients();
//	}
//
//	// JWT
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
//			/***
//			 * 重写增强token方法,用于自定义一些token总需要封装的信息
//			 */
//			@Override
//			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//				String userName = authentication.getUserAuthentication().getName();
//				// 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
//				final Map<String, Object> additionalInformation = new HashMap<>();
//				// Map假装用户实体
//				Map<String, String> userinfo = new HashMap<>();
//				userinfo.put("id", "1");
//				userinfo.put("username", "LiaoXiang");
//				userinfo.put("qqnum", "438944209");
//				userinfo.put("userFlag", "1");
//				additionalInformation.put("userinfo", JSON.toJSONString(userinfo));
//				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
//				OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
//				return enhancedToken;
//			}
//		};
//		// 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
//		accessTokenConverter.setSigningKey("SigningKey");
//		return accessTokenConverter;
//	}
//
//}