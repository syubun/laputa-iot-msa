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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.laputa.iot.upms.api.entity.SysUser;

import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.core.constant.enums.LoginTypeEnum;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.upms.api.feign.SmsRemoteService;
import com.laputa.iot.upms.mapper.SysUserMapper;
import com.laputa.iot.upms.service.MobileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sommer.jiang
 * @date 2018/11/14
 * <p>
 * 手机登录相关业务实现
 */
@Slf4j
@Service
@AllArgsConstructor
public class MobileServiceImpl implements MobileService {

	private final RedisTemplate redisTemplate;

	private final SysUserMapper userMapper;

	private final SmsRemoteService smsRemoteService;


	/**
	 * 发送手机验证码 TODO: 调用短信网关发送验证码,测试返回前端
	 * @param mobile mobile
	 * @return code
	 */
	@Override
	public R<Boolean> sendSmsCode(String mobile) {
		List<SysUser> userList = userMapper
				.selectList(Wrappers.<SysUser>query().lambda().eq(SysUser::getPhone, mobile));

		if (CollUtil.isEmpty(userList)) {
			log.info("手机号未注册:{}", mobile);
			return R.ok(Boolean.FALSE, "手机号未注册");
		}

		Object codeObj = redisTemplate.opsForValue()
				.get(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile);

		if (codeObj != null) {
			log.info("手机号验证码未过期:{}，{}", mobile, codeObj);
			return R.ok(Boolean.FALSE, "验证码发送过频繁");
		}

		String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
		redisTemplate.opsForValue().set(
				CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile, code,
				SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
		smsRemoteService.sendSms(mobile,code);
		return R.ok(Boolean.TRUE, code);
	}

}
