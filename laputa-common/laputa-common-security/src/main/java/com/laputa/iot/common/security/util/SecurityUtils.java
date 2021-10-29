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

package com.laputa.iot.common.security.util;

import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.common.security.service.LaputaUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 安全工具类
 *
 * @author Sommer.Jiang
 */
@UtilityClass
public class SecurityUtils {

	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 * @param authentication
	 * @return LaputaUser
	 * <p>
	 */
	public LaputaUser getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof LaputaUser) {
			return (LaputaUser) principal;
		}
		return null;
	}

	/**
	 * 获取用户
	 */
	public LaputaUser getUser() {
		Authentication authentication = getAuthentication();
		return getUser(authentication);
	}


	/**
	 * 获取用户角色信息
	 * @return 角色集合
	 */
	public List<SysRole> getRoles() {
//		Authentication authentication = getAuthentication();
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//		List<SysRole> roles = new ArrayList<>();
//		authorities.stream().filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
//				.forEach(granted -> {
//					String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
//					roles.add(Long.parseLong(id));
//				});
		Authentication authentication = getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof LaputaUser) {
			LaputaUser user = (LaputaUser) principal;
			Collection<? extends SysRole> userRoles = user.getRoles();
					List<SysRole> roles = new ArrayList<>();
			userRoles.stream().forEach(role -> {
				roles.add(role);
			});
			return roles;
		}
		return null;
	}

}
