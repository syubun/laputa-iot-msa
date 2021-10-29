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

package com.laputa.iot.common.security.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.laputa.iot.upms.api.entity.SysRole;
import lombok.Getter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author sommer.jiang
 * @date 2020/4/16 扩展用户信息
 */
public class LaputaUser extends User {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	/**
	 * 用户ID
	 */
	@Getter
	private Long id;

	/**
	 * 手机号
	 */
	@Getter
	private String phone;

	/**
	 * 头像
	 */
	@Getter
	private String avatar;

	/**
	 * 租户ID
	 */
	@Getter
	private Long tenantId;


	/**
	 * 用户昵称
	 */
	@Getter
	private String name;

	/**
	 * 权限
	 */
	@Getter
	private Collection<? extends SysRole> roles;

	/**
	 * Construct the <code>User</code> with the details required by
	 * {@link DaoAuthenticationProvider}.
	 * @param id 用户ID
	 * @param tenantId 租户ID
	 * @param username the username presented to the
	 * <code>DaoAuthenticationProvider</code>
	 * @param password the password that should be presented to the
	 * <code>DaoAuthenticationProvider</code>
	 * @param enabled set to <code>true</code> if the user is enabled
	 * @param accountNonExpired set to <code>true</code> if the account has not expired
	 * @param credentialsNonExpired set to <code>true</code> if the credentials have not
	 * expired
	 * @param accountNonLocked set to <code>true</code> if the account is not locked
	 * @param authorities the authorities that should be granted to the caller if they
	 * presented the correct username and password and the user is enabled. Not null.
	 * @throws IllegalArgumentException if a <code>null</code> value was passed either as
	 * a parameter or as an element in the <code>GrantedAuthority</code> collection
	 */
	@JsonCreator
	public LaputaUser(@JsonProperty("id") Long id,@JsonProperty("name") String name,
					  @JsonProperty("phone") String phone, @JsonProperty("avatar") String avatar,
					  @JsonProperty("tenantId") Long tenantId, @JsonProperty("username") String username,
					  @JsonProperty("password") String password, @JsonProperty("enabled") boolean enabled,
					  @JsonProperty("accountNonExpired") boolean accountNonExpired,
					  @JsonProperty("credentialsNonExpired") boolean credentialsNonExpired,
					  @JsonProperty("accountNonLocked") boolean accountNonLocked,
					  @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities,
					  @JsonProperty("roles") Collection<? extends SysRole> roles) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.phone = phone;
		this.avatar = avatar;
		this.tenantId = tenantId;
		this.roles = roles;
		this.name = name;
	}

}
