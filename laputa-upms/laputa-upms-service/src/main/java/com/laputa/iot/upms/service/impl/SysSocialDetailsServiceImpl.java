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

package com.laputa.iot.upms.service.impl;

import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.upms.api.dto.UserInfo;
import com.laputa.iot.upms.api.entity.SysSocialDetails;
import com.laputa.iot.upms.api.entity.SysUser;
import com.laputa.iot.upms.handler.LoginHandler;
import com.laputa.iot.upms.mapper.SysSocialDetailsMapper;
import com.laputa.iot.upms.mapper.SysUserMapper;
import com.laputa.iot.upms.service.SysSocialDetailsService;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author sommer.jiang
 * @date 2018年08月16日
 */
@Slf4j
@AllArgsConstructor
@Service("sysSocialDetailsService")
public class SysSocialDetailsServiceImpl extends ServiceImpl<SysSocialDetailsMapper, SysSocialDetails>
		implements SysSocialDetailsService {

	private final Map<String, LoginHandler> loginHandlerMap;

	private final CacheManager cacheManager;

	private final SysUserMapper sysUserMapper;

	/**
	 * 绑定社交账号
	 * @param type type
	 * @param code code
	 * @return
	 */
	@Override
	public Boolean bindSocial(String type, String code) {
		LoginHandler loginHandler = loginHandlerMap.get(type);
		// 绑定逻辑
		String identify = loginHandler.identify(code);
		SysUser sysUser = sysUserMapper.selectById(SecurityUtils.getUser().getId());
		loginHandler.bind(sysUser, identify);

		// 更新緩存
		cacheManager.getCache(CacheConstants.USER_DETAILS).evict(sysUser.getUsername());
		return Boolean.TRUE;
	}

	/**
	 * 根据入参查询用户信息
	 * @param inStr TYPE@code
	 * @return
	 */
	@Override
	public UserInfo getUserInfo(String inStr) {
		String[] inStrs = inStr.split(StringPool.AT);
		String type = inStrs[0];
		String loginStr = inStrs[1];
		return loginHandlerMap.get(type).handle(loginStr);
	}

}
