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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.upms.service.SysMenuService;
import com.laputa.iot.common.core.base.BaseController;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.base.vo.CheckExistVo;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sommer.jiang
 * @date 2017/10/31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单管理模块")
@Slf4j
public class SysMenuController extends BaseController<SysMenu> {
	public static final String MODULE_SN = "Menu:";
	public static final String MODULE_VIEW = "Menu:View";
	public static final String MODULE_ADD = "Menu:Add";
	public static final String MODULE_UPDATE = "Menu:Update";
	private final SysMenuService sysMenuService;


	/**
	 * 判断字段是否存在
	 *
	 * @param checkExistVo 参数
	 * @return
	 */

	@PostMapping(value = "/checkEntityExist")
	protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
		R<Boolean> booleanR = this.checkExist(sysMenuService, checkExistVo);
//		log.info("checkEntityExist:{}",booleanR);
		return booleanR;
	}


	@GetMapping(value = "/getAll")
	protected R getAllMenus( SysMenu sysMenu) {

		return R.ok(sysMenuService.getAllMenu());
	}

	/**
	 * 返回当前用户的树形菜单集合
	 * @param type 类型
	 * @param parentId 父节点ID
	 * @return 当前用户的树形菜单
	 */
	@GetMapping
	public R getUserMenu(String type, Long parentId) {

		// 获取符合条件的菜单
		Set<SysMenu> all = new HashSet<>();
		SecurityUtils.getRoles().forEach(role -> {
			log.info("role:{}",role);
			all.addAll(sysMenuService.findMenuByRoleId(role.getId()));
		});
		if(all.isEmpty()){
			return R.failed("无法得到有效的菜单。");
		}
		return R.ok(sysMenuService.filterMenu(all, type, parentId));
	}


	/**
	 * 返回当前用户的树形菜单集合
	 * @return 当前角色的菜单
	 */
	@GetMapping(value = "/getMenuByRoleId")
	public R getMenuByRoleId(String roleId) {
		List<SysMenu> menus = sysMenuService.findMenuByRoleId(Long.parseLong(roleId));

		return R.ok(menus);
	}


	/**
	 * 返回当前用户的树形菜单集合
	 * @return 当前角色的菜单
	 */
	@GetMapping(value = "/getAclsByRoleId")
	public R getAclsByRoleId(String roleId) {
		List<SysMenu> menus = sysMenuService.findAclsByRoleId(Long.parseLong(roleId));

		return R.ok(menus);
	}

	/**
	 * 返回树形菜单集合
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public R getTree(boolean lazy, Long parentId) {
		return R.ok(sysMenuService.treeMenu(lazy, parentId));
	}

	/**
	 * 返回全部树形菜单集合
	 * @return 树形菜单
	 */
	@GetMapping(value = "/menuTree")
	public R getMenuTree() {
		return R.ok(sysMenuService.allMenuTree());
	}

	/**
	 * 返回角色的菜单集合
	 * @param roleId 角色ID
	 * @return 属性集合
	 */
	@GetMapping("/tree/{roleId}")
	public R getMenuTreeByRoleId(@PathVariable Long roleId) {
		return R.ok(
				sysMenuService.findMenuByRoleId(roleId).stream().map(SysMenu::getId).collect(Collectors.toList()));
	}


	/**
	 * 返回角色的菜单集合
	 * @param roleId 角色ID
	 * @return 属性集合
	 */
	@GetMapping("/withoutCatTree/{roleId}")
	public R getWithoutCatMenuTreeByRoleId(@PathVariable Long roleId) {
		return R.ok(
				sysMenuService.findMenuWithoutCatByRoleId(roleId).stream().map(SysMenu::getId).collect(Collectors.toList()));
	}


	/**
	 * 通过ID查询菜单的详细信息
	 * @param id 菜单ID
	 * @return 菜单详细信息
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable Integer id) {
		return R.ok(sysMenuService.getById(id));
	}

	/**
	 * 新增菜单
	 * @param sysMenu 菜单信息
	 * @return success/false
	 */
	@SysLog("新增菜单")
	@PostMapping
//	@PreAuthorize("@pms.hasPermission('${MODULE_ADD}')")
	@PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Menu:add')") //只有角色是SYSTEM ADMIN两个才能操作
//	@PreAuthorize(value = "#oauth2.hasAnyScope('A','B','C','D')")//添加机构编码权限，判断该机构是否有权限调用
//	@PreAuthorize(value="isAuthenticated()")//添加登录权限判断，登录才可以调用
	public R save(@Valid @RequestBody SysMenu sysMenu) {

		return R.ok(sysMenuService.addMenu(sysMenu));
	}

	/**
	 * 删除菜单
	 * @param id 菜单ID
	 * @return success/false
	 */
	@SysLog("删除菜单")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('Menu:delete')")
	public R removeById(@PathVariable Long id) {
		return sysMenuService.removeMenuById(id);
	}

	/**
	 * 更新菜单
	 * @param sysMenu
	 * @return
	 */
	@SysLog("更新菜单")
	@PutMapping
	@PreAuthorize("hasRole('SYSTEM')")
	public R update(@Valid @RequestBody SysMenu sysMenu) {
		return R.ok(sysMenuService.updateMenuById(sysMenu));
	}


	/**
	 * 得到所有父菜单
	 * @param
	 * @return
	 */

	@GetMapping("/parents")
	public R getParentList() {
		return R.ok(sysMenuService.getParentList());
	}
	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysMenu 系统角色
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page" )
	public R getSysMenuPage(Page page, SysMenu sysMenu) {
//		log.info(page.toString(),sysMenu.toString());
		return R.ok(sysMenuService.page(page, Wrappers.query(sysMenu)));
	}


	@ApiOperation(value = "添加或保存", notes = "添加或保存")
	@PostMapping("/saveOrUpdate" )
	@Transactional(rollbackFor = Exception.class)
	public R saveOrUpdate(@Valid @RequestBody SysMenu sysMenu) {
		return R.ok(sysMenuService.saveOrUpdate(sysMenu));
	}





}
