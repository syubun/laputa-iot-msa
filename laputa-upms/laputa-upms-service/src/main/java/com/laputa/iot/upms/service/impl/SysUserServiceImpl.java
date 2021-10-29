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

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.upms.api.dto.UserDTO;
import com.laputa.iot.upms.api.dto.UserInfo;

import com.laputa.iot.upms.api.vo.UserVO;
import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.upms.api.entity.SysUser;
import com.laputa.iot.upms.entity.PrivilegeAcl;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.upms.entity.SysUserRole;
import com.laputa.iot.upms.service.*;
import com.laputa.iot.upms.mapper.SysUserMapper;

import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.base.dto.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sommer.jiang
 * @date 2017/10/31
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	private final SysMenuService sysMenuService;

	private final SysRoleService sysRoleService;

	private final SysUserRoleService sysUserRoleService;

	private final IAclService privilegeAclService;



	/**
	 * 保存用户信息
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUser(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setLockFlag(CommonConstants.STATUS_NORMAL);
		sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		baseMapper.insert(sysUser);
		List<SysUserRole> userRoleList = userDto.getRoles().stream().map(roleId -> {
			SysUserRole userRole = new SysUserRole();
			//sommer 20210723
			userRole.setUserId(sysUser.getId());
			userRole.setRoleId(roleId);
			return userRole;
		}).collect(Collectors.toList());
		return sysUserRoleService.saveBatch(userRoleList);
	}

	/**
	 * 通过查用户的全部信息
	 * @param sysUser 用户
	 * @return
	 */
	@Override
	public UserInfo findUserInfo(SysUser sysUser) {
		UserInfo userInfo = new UserInfo();
		userInfo.setSysUser(sysUser);
		// 设置角色列表 （ID）
		//sommer 20210723
		List<Long> roles = sysRoleService.findRolesByUserId(sysUser.getId()).stream().map(SysRole::getId)
				.collect(Collectors.toList());
		List<SysRole> roleCodes = sysRoleService.findRolesByUserId(sysUser.getId()).stream()
				.collect(Collectors.toList());
		userInfo.setRoles(ArrayUtil.toArray(roleCodes, SysRole.class));

		// 设置权限列表（menu.permission）
		Set<String> permissions = new HashSet<>();
		roles.forEach(roleId -> {
//			List<String> permissionList = sysMenuService.findMenuByRoleId(roleId).stream()
//					.filter(menu -> StrUtil.isNotEmpty(menu.getPermission())).map(SysMenu::getPermission)
//					.collect(Collectors.toList());
//			permissions.addAll(permissionList);
			List<SysMenu> menus = sysMenuService.findMenuByRoleId(roleId).stream().collect(Collectors.toList());
			menus.forEach(sysMenu -> {
				PrivilegeAcl aclByMenu = privilegeAclService.getAclByMenu(sysMenu.getId());
				sysMenu.setPermission(aclByMenu.getPvsPermission());
				aclByMenu.getPvs().forEach(privilegeValue -> {
					permissions.add(privilegeValue.getPermissionName())	;
				});
//				permissions.add(aclByMenu.getPvsPermission());
			});

		});

		userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));

		return userInfo;
	}

	/**
	 * 分页查询用户信息（含有角色信息）
	 * @param page 分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	@Override
	public IPage getUsersWithRolePage(Page page, UserDTO userDTO) {

		return baseMapper.getUserVosPage(page, userDTO);
	}

	/**
	 * 通过ID查询用户信息
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserVO selectUserVoById(Long id) {
		return baseMapper.getUserVoById(id);
	}

	/**
	 * 删除用户
	 * @param sysUser 用户
	 * @return Boolean
	 */
	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#sysUser.username")
	public Boolean deleteUserById(SysUser sysUser) {
		sysUserRoleService.deleteByUserId(sysUser.getId());
		this.removeById(sysUser.getId());
		return Boolean.TRUE;
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userDto.username")
	public R<Boolean> updateUserInfo(UserDTO userDto) {
		UserVO userVO = baseMapper.getUserVoByUsername(userDto.getUsername());
		if (!ENCODER.matches(userDto.getPassword(), userVO.getPassword())) {
			log.info("原密码错误，修改个人信息失败:{}", userDto.getUsername());
			return R.failed("原密码错误，修改个人信息失败");
		}

		SysUser sysUser = new SysUser();
		if (StrUtil.isNotBlank(userDto.getNewpassword1())) {
			sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
		}
		if(StringUtils.isNotBlank(userDto.getPhone())&& !userDto.getPhone().contains("*")){
			sysUser.setPhone(userDto.getPhone());
		}

		sysUser.setId(userVO.getId());
		sysUser.setAvatar(userDto.getAvatar());
		return R.ok(this.updateById(sysUser));
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userDto.username")
	public Boolean updateUser(UserDTO userDto) {
//		log.info(userDto.toString());
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(userDto.getPassword())) {
			sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		}
		this.updateById(sysUser);
		if(userDto.getRoles()==null||userDto.getRoles().size()==0){
			return true;
		}
		sysUserRoleService
				.remove(Wrappers.<SysUserRole>update().lambda().eq(SysUserRole::getUserId, userDto.getId()));
		userDto.getRoles().forEach(roleId -> {
			SysUserRole userRole = new SysUserRole();
			//sommer 20210723
			userRole.setUserId(sysUser.getId());
			userRole.setRoleId(roleId);
			userRole.insert();
		});
		return Boolean.TRUE;
	}

	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#user.username")
	@Override
	public Boolean updateUser(SysUser user) {
		log.info(user.toString());
		user.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(user.getPassword())) {
			user.setPassword(null);
		}
		this.updateById(user);
		return true;
	}



}
