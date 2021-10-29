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

package com.laputa.iot.upms.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.upms.api.dto.RoleDTO;
import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.upms.api.vo.RoleVO;
import com.laputa.iot.upms.service.SysRoleService;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author sommer.jiang
 * @date 2020-02-10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(value = "role", tags = "角色管理模块")
public class SysRoleController {

	private final SysRoleService sysRoleService;

	/**
	 * 通过ID查询角色信息
	 * @param id ID
	 * @return 角色信息
	 */

//	@ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType="path", dataType = "Long")
	@GetMapping("/{id}")
	public R getById(@PathVariable String id) {
		Long roleId = Long.parseLong(id);
		return R.ok(sysRoleService.getById(roleId));
	}

	/**
	 * 添加角色
	 * @param roleDTO 角色信息
	 * @return success、false
	 */
	@SysLog("添加角色")
	@PostMapping
//	@PreAuthorize("@pms.hasPermission('sys_role_add')")

//	@CacheEvict(value = CacheConstants.ROLE_DETAILS,key = "#roleDTO.id+'-'+#roleDTO.name", allEntries = true)
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	public R save(@Valid @RequestBody RoleDTO roleDTO) {
		return R.ok(sysRoleService.addRole(roleDTO));
	}

	/**
	 * 修改角色
	 * @param roleDTO 角色信息
	 * @return success/false
	 */
	@SysLog("修改角色")
	@PutMapping
//	@PreAuthorize("@pms.hasPermission('sys_role_edit')")
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	public R update(@Valid @RequestBody RoleDTO roleDTO) {
		return R.ok(sysRoleService.updateRole(roleDTO));
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@SysLog("删除角色")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_role_del')")
	@CacheEvict(value = CacheConstants.ROLE_DETAILS, allEntries = true)
	public R removeById(@PathVariable Long id) {
		return R.ok(sysRoleService.removeRoleById(id));
	}

	/**
	 * 获取角色列表
	 * @return 角色列表
	 */
	@GetMapping("/list")
	public R listRoles() {
		return R.ok(sysRoleService.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 分页查询角色信息
	 * @param page 分页对象
	 * @param role 查询条件
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R getRolePage(Page page, SysRole role) {
		return R.ok(sysRoleService.page(page, Wrappers.query(role)));
	}

	/**
	 * 更新角色菜单
	 * @param roleVo 角色对象
	 * @return success、false
	 */
	@SysLog("更新角色菜单")
	@PutMapping("/menu")
	@PreAuthorize("@pms.hasPermission('sys_role_perm')")
	public R saveRoleMenus(@RequestBody RoleVO roleVo) {
		return R.ok(sysRoleService.updateRoleMenus(roleVo));
	}

	/**
	 * 通过角色ID 查询角色列表
	 * @param roleIdList 角色ID
	 * @return
	 */
	@PostMapping("/getRoleList")
	public R getRoleList(@RequestBody List<Long> roleIdList) {
		return R.ok(sysRoleService.findRolesByRoleIds(roleIdList, CollUtil.join(roleIdList, StrUtil.UNDERLINE)));
	}


	/**
	 * 更新角色的权限
	 * @param roleDTO 角色ID
	 * @return
	 */
	@PostMapping("/updateRolePermission")
	public R updateRolePermission(@RequestBody RoleDTO roleDTO) {
		return R.ok(sysRoleService.updateRolePermission(roleDTO));
	}



	/**
	 * 更新角色的权限
	 * @param role 角色
	 * @return
	 */
	@PutMapping("/setStatus")
	public R setRoleStatus(@RequestBody RoleDTO role) {

		return R.ok(sysRoleService.updateRole(role));
	}

}
