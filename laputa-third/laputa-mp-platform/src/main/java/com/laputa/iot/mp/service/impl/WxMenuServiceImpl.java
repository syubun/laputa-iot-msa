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
package com.laputa.iot.mp.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.mp.config.WxMpInitConfigRunner;
import com.laputa.iot.mp.entity.WxAccount;
import com.laputa.iot.mp.mapper.WxAccountMapper;
import com.laputa.iot.mp.entity.WxMpMenu;
import com.laputa.iot.mp.mapper.WxMenuMapper;
import com.laputa.iot.mp.service.WxMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 微信菜单业务
 *
 * @author sommer.jiang
 * @date 2019-03-27 20:45:18
 */
@Slf4j
@Service
@AllArgsConstructor
public class WxMenuServiceImpl extends ServiceImpl<WxMenuMapper, WxMpMenu> implements WxMenuService {

	private static final String PUB_ED = "1";

	private final WxAccountMapper wxAccountMapper;

	/**
	 * 新增微信公众号按钮
	 * @param wxMenus json
	 * @param appId 公众号
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean save(JSONObject wxMenus, String appId) {
		baseMapper.delete(Wrappers.<WxMpMenu>lambdaQuery().eq(WxMpMenu::getWxAccountAppid, appId));

		WxAccount wxAccount = wxAccountMapper
				.selectOne(Wrappers.<WxAccount>lambdaQuery().eq(WxAccount::getAppid, appId));
		WxMpMenu wxMpMenu = new WxMpMenu();
		wxMpMenu.setMenu(wxMenus.toStringPretty());
		wxMpMenu.setWxAccountId(wxAccount.getId());
		wxMpMenu.setWxAccountAppid(wxAccount.getAppid());
		wxMpMenu.setWxAccountName(wxAccount.getName());
		baseMapper.insert(wxMpMenu);
		return Boolean.TRUE;
	}

	/**
	 * 发布到微信
	 * @param appId 公众号信息
	 * @return
	 */
	@Override
	public R push(String appId) {
		List<WxMpMenu> wxMpMenuList = baseMapper
				.selectList(Wrappers.<WxMpMenu>lambdaQuery().eq(WxMpMenu::getWxAccountAppid, appId));

		if (CollUtil.isEmpty(wxMpMenuList)) {
			return R.failed("微信菜单配置未保存，不能发布");
		}
		WxMpMenu wxMpMenu = wxMpMenuList.get(0);
		// 判断是否发布
		if (PUB_ED.equals(wxMpMenu.getPubFlag())) {
			return R.failed("微信菜单配置已发布，不要重复发布");
		}
		WxMpService wxMpService = WxMpInitConfigRunner.getMpServices().get(appId);
		WxMpMenuService menuService = wxMpService.getMenuService();

		// 给数据库保存的加一层
		try {
			menuService.menuCreate(wxMpMenu.getMenu());
		}
		catch (WxErrorException e) {
			log.error("发布微信菜单失败", e.getError().getErrorMsg());
			return R.failed(e.getError().getErrorMsg());
		}

		// 更新菜单发布标志
		wxMpMenu.setPubFlag(PUB_ED);
		baseMapper.updateById(wxMpMenu);
		return R.ok();
	}

	/**
	 * 通过appid 查询菜单信息
	 * @param appId
	 * @return
	 */
	@Override
	public R getByAppId(String appId) {
		List<WxMpMenu> wxMpMenuList = baseMapper
				.selectList(Wrappers.<WxMpMenu>lambdaQuery().eq(WxMpMenu::getWxAccountAppid, appId));

		if (CollUtil.isEmpty(wxMpMenuList)) {
			return R.ok();
		}
		return R.ok(wxMpMenuList.get(0).getMenu());
	}

	/**
	 * 通过appid 删除菜单
	 * @param appId
	 * @return
	 */
	@Override
	public R delete(String appId) {
		WxMpService wxMpService = WxMpInitConfigRunner.getMpServices().get(appId);
		WxMpMenuService menuService = wxMpService.getMenuService();
		try {
			menuService.menuDelete();
		}
		catch (WxErrorException e) {
			log.error("微信菜单删除失败", e.getError().getErrorMsg());
			return R.failed(e.getError().getErrorMsg());
		}

		baseMapper.delete(Wrappers.<WxMpMenu>lambdaQuery().eq(WxMpMenu::getWxAccountAppid, appId));
		return R.ok();
	}

}
