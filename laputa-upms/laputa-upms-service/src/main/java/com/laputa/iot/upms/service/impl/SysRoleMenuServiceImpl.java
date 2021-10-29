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

package com.laputa.iot.upms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.upms.entity.SysRoleMenu;
import com.laputa.iot.upms.mapper.SysMenuMapper;
import com.laputa.iot.upms.mapper.SysRoleMenuMapper;
import com.laputa.iot.upms.service.SysRoleMenuService;
import com.laputa.iot.common.core.constant.CacheConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author sommer.jiang
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
@Slf4j
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

	private final CacheManager cacheManager;

	private final SysMenuMapper menuMapper;

//	private final SysMenuServiceImpl sysMenuService;

	/**
	 * @param role
	 * @param roleId 角色
	 * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.MENU_DETAILS, key = "#roleId")
	public Boolean saveRoleMenus(String role, Long roleId, String menuIds) {
		log.info(menuIds);
		if (StrUtil.isBlank(menuIds)) {
			return Boolean.FALSE;
		}
		this.remove(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, roleId));

		List<SysRoleMenu> roleMenuList = Arrays.stream(menuIds.split(",")).map(menuId -> {
			SysRoleMenu roleMenu = new SysRoleMenu();
			SysMenu menu = menuMapper.selectById(Long.valueOf(menuId));
			roleMenu.setRoleId(roleId);
			roleMenu.setRoleCode(role);
			roleMenu.setMenuSn(menu.getTag());
			roleMenu.setMenuId(Long.valueOf(menuId));
			return roleMenu;
		}).collect(Collectors.toList());



		baseMapper.insertBatchSomeColumn(roleMenuList);
		// 清空userinfo
		cacheManager.getCache(CacheConstants.USER_DETAILS).clear();
		return Boolean.TRUE;
	}

}
