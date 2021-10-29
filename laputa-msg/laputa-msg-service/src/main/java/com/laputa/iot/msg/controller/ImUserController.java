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

package com.laputa.iot.msg.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.msg.api.entity.ImUser;
import com.laputa.iot.msg.service.ImUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 聊天用户
 *
 * @author Sommer
 * @date 2021-10-23 14:18:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/imuser" )
@Api(value = "imuser", tags = "聊天用户管理")
public class ImUserController {

    private static final String MODULE_SN = "ImUser:";
    private final  ImUserService imUserService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param imUser 聊天用户
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('ImUser:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getImUserPage(Page page, ImUser imUser) {
        return R.ok(imUserService.page(page, Wrappers.query(imUser)));
    }


    /**
     * 通过id查询聊天用户
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('ImUser:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(imUserService.getById(id));
    }

    /**
     * 新增聊天用户
     * @param imUser 聊天用户
     * @return R
     */
    @ApiOperation(value = "新增聊天用户", notes = "新增聊天用户")
    @SysLog("新增聊天用户" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('ImUser:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody ImUser imUser) {
        return R.ok(imUserService.save(imUser));
    }

    /**
     * 修改聊天用户
     * @param imUser 聊天用户
     * @return R
     */
    @ApiOperation(value = "修改聊天用户", notes = "修改聊天用户")
    @SysLog("修改聊天用户" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('ImUser:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody ImUser imUser) {
        return R.ok(imUserService.updateById(imUser));
    }

    /**
     * 通过id删除聊天用户
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除聊天用户", notes = "通过id删除聊天用户")
    @SysLog("通过id删除聊天用户" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('ImUser:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R deleteById(@PathVariable Long id) {
        return R.ok(imUserService.removeById(id));
    }

}
