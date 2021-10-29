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

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.upms.api.dto.RoleDTO;
import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.upms.entity.SysRoleMenu;
import com.laputa.iot.upms.api.vo.RoleVO;
import com.laputa.iot.upms.service.SysRoleMenuService;
import com.laputa.iot.upms.service.SysRoleService;
import com.laputa.iot.upms.mapper.SysRoleMapper;
import com.laputa.iot.common.core.constant.CacheConstants;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sommer.jiang
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 通过用户ID，查询角色信息
	 * @param userId
	 * @return
	 */
	@Override
	@Cacheable(value = CacheConstants.ROLE_DETAILS, key = "#userId")
	public List findRolesByUserId(Long userId) {
		return baseMapper.listRolesByUserId(userId);
	}

	/**
	 * 根据角色ID 查询角色列表，注意缓存删除
	 * @param roleIdList 角色ID列表
	 * @param key 缓存key
	 * @return
	 */
	@Override
	@Cacheable(value = CacheConstants.ROLE_DETAILS, key = "#key")
	public List<SysRole> findRolesByRoleIds(List<Long> roleIdList, String key) {
		return baseMapper.selectBatchIds(roleIdList);
	}

	/**
	 * 通过角色ID，删除角色,并清空角色菜单缓存
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeRoleById(Long id) {
		sysRoleMenuService.remove( Wrappers.<SysRoleMenu>update().lambda().eq(SysRoleMenu::getRoleId, id));
		return this.removeById(id);
	}

	/**
	 * 根据角色菜单列表
	 * @param roleVo 角色&菜单列表
	 * @return
	 */
	@Override
	public Boolean updateRoleMenus(RoleVO roleVo) {
		SysRole sysRole = baseMapper.selectById(roleVo.getRoleId());
		return sysRoleMenuService.saveRoleMenus(sysRole.getCode(), roleVo.getRoleId(), roleVo.getMenuIds());
	}

	@Override
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public Boolean addRole(RoleDTO roleDTO) {
		SysRole sysRole = new SysRole();
		BeanUtil.copyProperties(roleDTO,sysRole);
		save(sysRole);
		return sysRoleMenuService.saveRoleMenus(sysRole.getCode(), sysRole.getId(), roleDTO.getMenuIds());
	}

	@Override
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateRole(RoleDTO roleDTO) {
		SysRole sysRole = new SysRole();
		BeanUtil.copyProperties(roleDTO,sysRole);
		return updateById(sysRole);
	}

	@Override
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateRolePermission(RoleDTO roleDTO) {
		return sysRoleMenuService.saveRoleMenus(roleDTO.getCode(), roleDTO.getId(), roleDTO.getMenuIds());
	}

}
