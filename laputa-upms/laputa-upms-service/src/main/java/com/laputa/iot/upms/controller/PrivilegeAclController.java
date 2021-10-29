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

package com.laputa.iot.upms.controller;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.upms.api.dto.AclDTO;
import com.laputa.iot.upms.api.dto.PrivilegeValueDTO;
import com.laputa.iot.upms.entity.PrivilegeValue;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.upms.entity.PrivilegeAcl;
import com.laputa.iot.upms.service.IAclService;
import com.laputa.iot.common.security.util.SecurityUtils;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 模块权限表
 *
 * @author Sommer
 * @date 2021-09-08 12:48:31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/acl" )
@Api(value = "acl", tags = "模块权限表管理")
public class PrivilegeAclController {

    private static final String MODULE_SN = "Acl:";
    private final IAclService privilegeAclService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param privilegeAcl 模块权限表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('Acl:view')" )
    public R getPrivilegeAclPage(Page page, PrivilegeAcl privilegeAcl) {
        return R.ok(privilegeAclService.page(page, Wrappers.query(privilegeAcl)));
    }


    /**
     * 通过id查询模块权限表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('Acl:view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(privilegeAclService.getById(id));
    }

    /**
     * 新增模块权限表
     * @param privilegeAcl 模块权限表
     * @return R
     */
    @ApiOperation(value = "新增模块权限表", notes = "新增模块权限表")
    @SysLog("新增模块权限表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('Acl:add')" )
    public R save(@RequestBody PrivilegeAcl privilegeAcl) {
        return R.ok(privilegeAclService.save(privilegeAcl));
    }

    /**
     * 修改模块权限表
     * @param privilegeAcl 模块权限表
     * @return R
     */
    @ApiOperation(value = "修改模块权限表", notes = "修改模块权限表")
    @SysLog("修改模块权限表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('Acl:update')" )
    public R updateById(@RequestBody PrivilegeAcl privilegeAcl) {
        return R.ok(privilegeAclService.updateById(privilegeAcl));
    }

    /**
     * 通过id删除模块权限表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除模块权限表", notes = "通过id删除模块权限表")
    @SysLog("通过id删除模块权限表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('Acl:delete')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(privilegeAclService.removeById(id));
    }

    /**
     * 通过id删除模块权限表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除模块权限表", notes = "通过id删除模块权限表")
    @SysLog("通过id得到模块权限表" )
    @GetMapping("/getAclByMenu/{id}" )
    public R getAclByMenu(@PathVariable Long id) {
        return R.ok(privilegeAclService.getAclByMenu(id));
    }

    /**
     * 得到登录用户的权鉴表
     * @return R
     */
    @ApiOperation(value = "得到登录用户的权鉴表", notes = "得到登录用户的权鉴表")
    @SysLog("得到登录用户的权鉴表" )
    @GetMapping("getPermCode" )
    public R getPermCode() {
        return R.ok(   SecurityUtils.
                getAuthentication().
                getAuthorities().
                stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }


    /**
     * 设置权限值
     * @param aclDTO 模块
     * @return
     */
    @SysLog("更新角色菜单的权限值" )
    @PostMapping(value = "/updatePriVal" )
    public R<Boolean> updatePriVal(@RequestBody AclDTO aclDTO) {
        return privilegeAclService.updatePriVal( aclDTO);
    }


    /**
     * 通过id删除模块权限表
     * @param aclDTO
     * @return R
     */
    @ApiOperation(value = "通过id删除模块权限表", notes = "通过id删除模块权限表")
    @SysLog("通过id得到模块权限表" )
    @GetMapping("/getAclByRoleMenu" )
    public R getAclByRoleMenu(@RequestParam Long  menuId, @RequestParam Long roleId) {
        return R.ok(privilegeAclService.getAclByRoleMenu(menuId,roleId));
    }

}
