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

package com.laputa.iot.upms.api.feign;

import com.laputa.iot.upms.api.dto.UserInfo;
import com.laputa.iot.upms.api.entity.SysUser;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.core.base.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * @author sommer.jiang
 * @date 2021/6/22
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteUserService {

	/**
	 * 通过用户名查询用户、角色信息
	 * @param username 用户名
	 * @param from 调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<UserInfo> info(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);


	/**
	 * 得到所有的用户
	 * @param
	 * @param from 调用标志
	 * @return R
	 */
	@GetMapping("/user/all")
	R<List<SysUser>> all(@RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号或手机号查询用户、角色信息
	 * @param inStr appid@code
	 * @param from 调用标志
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	R<UserInfo> social(@PathVariable("inStr") String inStr, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询上级部门的用户信息
	 * @param username 用户名
	 * @return R
	 */
	@GetMapping("/user/ancestor/{username}")
	R<List<SysUser>> ancestorUsers(@PathVariable("username") String username);

}
