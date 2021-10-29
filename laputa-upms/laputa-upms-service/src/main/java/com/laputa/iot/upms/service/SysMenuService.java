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

package com.laputa.iot.upms.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.common.core.base.dto.R;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author sommer.jiang
 * @since 2017-10-29
 */
public interface SysMenuService extends IService<SysMenu> {

	/**
	 * 通过角色编号查询URL 权限
	 * @param roleId 角色ID
	 * @return 菜单列表
	 */
	List<SysMenu> findMenuByRoleId(Long roleId);

	/**
	 * 通过角色编号查询URL菜单权限 排除父ID
	 * @param roleId 角色ID
	 * @return 菜单列表
	 */
	 List<SysMenu> findMenuWithoutCatByRoleId(Long roleId);

	/**
	 * 级联删除菜单
	 * @param id 菜单ID
	 * @return 成功、失败
	 */
	R removeMenuById(Long id);

	/**
	 * 更新菜单信息
	 * @param sysMenu 菜单信息
	 * @return 成功、失败
	 */
	Boolean updateMenuById(SysMenu sysMenu);

	/**
	 * 构建树
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return
	 */
	List<Tree<Long>> treeMenu(boolean lazy, Long parentId);

	/**
	 * 构建树
	 * @return
	 */
	List<Tree<Long>> allMenuTree();

	/**
	 * 查询菜单
	 * @param voSet
	 * @param parentId
	 * @return
	 */
	List<Tree<Long>> filterMenu(Set<SysMenu> voSet, String type, Long parentId);

	/**
	 * 得到所有的父级菜单
	 * @return
	 */
	List<SysMenu> getParentList();

	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	Boolean addMenu(SysMenu menu) ;

	/**
	 * 得到所有的菜单 包括子菜单
	 * @return  List<SysMenu>
	 */
	 List<SysMenu> getAllMenu();

	/**
	 * 得到所有的菜单 包括子菜单
	 * @return  List<SysMenu>
	 */
	List<SysMenu> findAclsByRoleId(Long roleId);
}
