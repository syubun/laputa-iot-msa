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

package com.laputa.iot.upms.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.laputa.iot.common.core.base.entity.BaseEntity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * 系统菜单
 *
 * @author sommer.jiang
 * @date 2021-07-26 10:37:00
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("sys_menu")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统菜单")
public class SysMenu extends BaseEntity<Long> {


    private static final long serialVersionUID = -8081284932302238566L;
    /**
     * 菜单名称
     */

    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 菜单权限标识
     */
    @ApiModelProperty(value = "菜单权限标识")
    private String permission;

    /**
     * 前端路由组件
     */
    @ApiModelProperty(value = "前端路由组件")
    private String component;

    /**
     * 前端路由参数
     */
    @ApiModelProperty(value = "前端路由参数")
    private String alias;

    /**
     * 前端路由标识路径
     */
    @ApiModelProperty(value = "前端路由标识路径")
    private String path;

    /**
     * 路径参数
     */
    @ApiModelProperty(value = "路径参数")
    private String parampath;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    /**
     * 排序值
     */
    @ApiModelProperty(value = "排序值")
    private Integer sort;

    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型")
    private Integer type;

    /**
     * 菜单重定向
     */
    @ApiModelProperty(value = "菜单重定向")
    private String redirect;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 是否可用状态 0启用 1禁用
     */
    @ApiModelProperty(value = "是否可用状态 0启用 1禁用")
    private Boolean disabled;

    /**
     * 是否忽略权限 0鉴权 1忽略
     */
    @ApiModelProperty(value = "是否忽略权限 0鉴权 1忽略")
    private Boolean ignoreAuth;

    /**
     * 是否固定头部 0不固定 1固定
     */
    @ApiModelProperty(value = "是否固定头部 0不固定 1固定")
    private Boolean affix;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String tag;

    /**
     * 路由缓存
     */
    @ApiModelProperty(value = "路由缓存")
    private Boolean ignoreKeepAlive;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 隐藏菜单
     */
    @ApiModelProperty(value = "隐藏菜单")
    private Boolean hideMenu;

    /**
     * 隐藏Tab标签
     */
    @ApiModelProperty(value = "隐藏Tab标签")
    private Boolean hideTab;

    /**
     * 显示菜单
     */
    @ApiModelProperty(value = "显示菜单")
    private Boolean showMenu;

    /**
     * 当前激活菜单
     */
    @ApiModelProperty(value = "当前激活菜单")
    private String currentActiveMenu;

    /**
     * Iframe的源链接
     */
    @ApiModelProperty(value = "Iframe的源链接")
    private String frameSrc;

    /**
     * 动画类型
     */
    @ApiModelProperty(value = "动画类型")
    private String transitionName;

    /**
     * 显示在头部指引中 0显示 1隐藏
     */
    @ApiModelProperty(value = "显示在头部指引中 0显示 1隐藏")
    private Boolean hideBreadcrumb;

    /**
     * 显示子菜单 0显示 1隐藏
     */
    @ApiModelProperty(value = "显示子菜单 0显示 1隐藏")
    private Boolean hideChildrenInMenu;

    /**
     * 隐藏子菜单路径 0显示 1隐藏
     */
    @ApiModelProperty(value = "隐藏子菜单路径 0显示 1隐藏")
    private Boolean hideChildrenInPath;

    /**
     * 是否带参数 0不带 1带
     */
    @ApiModelProperty(value = "是否带参数 0不带 1带")
    private Boolean carryParam;

    /**
     * 是否带但菜单
     */
    @ApiModelProperty(value = "是否带但菜单")
    private Boolean single;

    /**
     * 是否是链接
     */
    @ApiModelProperty(value = "是否是链接")
    private Boolean isLink;

//    /* 每个模块绑定的权限值 */
//    private BigInteger state;

    /**
     * 临时变量 操作权限集合
     */
    @TableField(exist = false)
    private List<PrivilegeValue> pvs;

    /**
     * 临时变量 操作权限
     */
    @TableField(exist = false)
    private PrivilegeAcl acl;

    /**
     * 是否纯菜单 不带路由, 0带路由 1不带路由
     */
    @ApiModelProperty(value = "是否纯菜单 不带路由, 0带路由 1不带路由")
    private Boolean ignoreRoute;

    @Builder
    public SysMenu(Long id,
                   String name,
                   String permission,
                   String component,
                   String alias,
                   String path,
                   String parampath,
                   Long parentId,
                   Integer sort, Integer type, String redirect, String title, Boolean disabled, Boolean ignoreAuth, Boolean affix, String tag, Boolean ignoreKeepAlive, String icon, Boolean hideMenu, Boolean hideTab, Boolean showMenu, String currentActiveMenu, String frameSrc, String transitionName, Boolean hideBreadcrumb, Boolean hideChildrenInMenu, Boolean hideChildrenInPath, Boolean carryParam, Boolean single, Boolean isLink, Boolean ignoreRoute) {
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.component = component;
        this.alias = alias;
        this.path = path;
        this.parampath = parampath;
        this.parentId = parentId;
        this.sort = sort;
        this.type = type;
        this.redirect = redirect;
        this.title = title;
        this.disabled = disabled;
        this.ignoreAuth = ignoreAuth;
        this.affix = affix;
        this.tag = tag;
        this.ignoreKeepAlive = ignoreKeepAlive;
        this.icon = icon;
        this.hideMenu = hideMenu;
        this.hideTab = hideTab;
        this.showMenu = showMenu;
        this.currentActiveMenu = currentActiveMenu;
        this.frameSrc = frameSrc;
        this.transitionName = transitionName;
        this.hideBreadcrumb = hideBreadcrumb;
        this.hideChildrenInMenu = hideChildrenInMenu;
        this.hideChildrenInPath = hideChildrenInPath;
        this.carryParam = carryParam;
        this.single = single;
        this.isLink = isLink;
        this.ignoreRoute = ignoreRoute;
        this.tenantId = 1L;
    }





}
