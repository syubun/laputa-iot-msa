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

package com.laputa.iot.upms.handler;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laputa.iot.upms.api.dto.UserInfo;
import com.laputa.iot.upms.api.entity.SysSocialDetails;
import com.laputa.iot.upms.api.entity.SysUser;
import com.laputa.iot.upms.mapper.SysSocialDetailsMapper;
import com.laputa.iot.upms.service.SysUserService;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.core.constant.enums.LoginTypeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author sommer.jiang
 * @date 2018/11/18
 */
@Slf4j
@Component("WX")
@AllArgsConstructor
public class WeChatLoginHandler extends AbstractLoginHandler {

	private final SysUserService sysUserService;

	private final SysSocialDetailsMapper sysSocialDetailsMapper;

	/**
	 * 微信登录传入code
	 * <p>
	 * 通过code 调用qq 获取唯一标识
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
		SysSocialDetails condition = new SysSocialDetails();
		condition.setType(LoginTypeEnum.WECHAT.getType());
		SysSocialDetails socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));

		String url = String.format(SecurityConstants.WX_AUTHORIZATION_CODE_URL, socialDetails.getAppId(),
				socialDetails.getAppSecret(), code);
		String result = HttpUtil.get(url);
		log.debug("微信响应报文:{}", result);

		Object obj = JSONUtil.parseObj(result).get("openid");
		return obj.toString();
	}

	/**
	 * openId 获取用户信息
	 * @param openId
	 * @return
	 */
	@Override
	public UserInfo info(String openId) {
		//sommer 20210723
//		SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getWxOpenid, openId));
//
//		if (user == null) {
//			log.info("微信未绑定:{}", openId);
//			return null;
//		}
//		return sysUserService.findUserInfo(user);
		return null;
	}

	/**
	 * 绑定逻辑
	 * @param user 用户实体
	 * @param identify 渠道返回唯一标识
	 * @return
	 */
	@Override
	public Boolean bind(SysUser user, String identify) {
		//sommer 20210723
//		List<SysUser> userList = sysUserService
//				.list(Wrappers.<SysUser>query().lambda().eq(SysUser::getWxOpenid, identify));
//
//		// 先把原有绑定关系去除,设置绑定为NULL
//		if (CollUtil.isNotEmpty(userList)) {
//			SysUser condition = new SysUser();
//			condition.setWxOpenid(identify);
//			sysUserService.update(condition, Wrappers.<SysUser>lambdaUpdate().set(SysUser::getWxOpenid, null));
//			log.info("微信账号 {} 更换账号绑定", identify);
//		}
//
//		user.setWxOpenid(identify);
//		sysUserService.updateById(user);
		return null;
	}

}
