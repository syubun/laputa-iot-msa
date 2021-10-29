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

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.security.exception.LaputaAuth2Exception;
import com.laputa.iot.common.security.service.LaputaUser;
import com.laputa.iot.common.security.util.LaputaSecurityMessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author sommer.jiang
 * @date 2021-03-07
 * <p>
 * 根据checktoken 的结果转化用户信息
 */
@Slf4j
public class LaputaUserAuthenticationConverter implements UserAuthenticationConverter {

	private static final String N_A = "N/A";

	/**
	 * Extract information about the user to be used in an access token (i.e. for resource
	 * servers).
	 * @param authentication an authentication representing a user
	 * @return a map of key values representing the unique information about the user
	 */
	@Override
	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		Map<String, Object> response = new LinkedHashMap<>();
		response.put(USERNAME, authentication.getName());
		if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
			response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}
		return response;
	}

	/**
	 * Inverse of {@link #convertUserAuthentication(Authentication)}. Extracts an
	 * Authentication from a map.
	 * @param responseMap a map of user information
	 * @return an Authentication representing the user or null if there is none
	 */
	@Override
	public Authentication extractAuthentication(Map<String, ?> responseMap) {
		if (responseMap.containsKey(USERNAME)) {
			Collection<? extends GrantedAuthority> authorities = getAuthorities(responseMap);
			Map<String, ?> map = MapUtil.get(responseMap, SecurityConstants.DETAILS_USER, Map.class);
			validateTenantId(map);
			String username = MapUtil.getStr(map, SecurityConstants.DETAILS_USERNAME);
			Long id = MapUtil.getLong(map, SecurityConstants.DETAILS_USER_ID);
			Long tenantId = MapUtil.getLong(map, SecurityConstants.DETAILS_TENANT_ID);
			String phone = MapUtil.getStr(map, SecurityConstants.DETAILS_PHONE);
			String avatar = MapUtil.getStr(map, SecurityConstants.DETAILS_AVATAR);
			String name = MapUtil.getStr(map, SecurityConstants.DETAILS_NAME);
			Collection<? extends SysRole> roles = MapUtil.get(map, SecurityConstants.DETAILS_ROLES, Collection.class);
			LaputaUser user = new LaputaUser((Long)id,name,  phone, avatar, tenantId, username, N_A, true, true, true, true,
					authorities,roles);
			return new UsernamePasswordAuthenticationToken(user, N_A, authorities);
		}
		return null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
		Object authorities = map.get(AUTHORITIES);
		if (authorities instanceof String) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
		}
		if (authorities instanceof Collection) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList(
					StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));
		}
		return AuthorityUtils.NO_AUTHORITIES;
	}

	private void validateTenantId(Map<String, ?> map) {
		String headerValue = getCurrentTenantId();
		Integer userValue = MapUtil.getInt(map, SecurityConstants.DETAILS_TENANT_ID);
		if (StrUtil.isNotBlank(headerValue) && !userValue.toString().equals(headerValue)) {
			log.warn("请求头中的租户ID({})和用户的租户ID({})不一致", headerValue, userValue);
			// TODO: 不要提示租户ID不对，可能被穷举
			throw new LaputaAuth2Exception(LaputaSecurityMessageSourceUtil.getAccessor().getMessage(
					"AbstractUserDetailsAuthenticationProvider.badTenantId", new Object[] { headerValue },
					"Bad tenant ID"));
		}
	}

	private Optional<HttpServletRequest> getCurrentHttpRequest() {
		return Optional.ofNullable(RequestContextHolder.getRequestAttributes()).filter(
				requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
				.map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
				.map(ServletRequestAttributes::getRequest);
	}

	private String getCurrentTenantId() {
		return getCurrentHttpRequest()
				.map(httpServletRequest -> httpServletRequest.getHeader(CommonConstants.TENANT_ID)).orElse(null);
	}

}
