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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.upms.entity.PrivilegeValue;
import com.laputa.iot.upms.service.PrivilegeValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 权限键值表
 *
 * @author Sommer
 * @date 2021-09-08 12:57:42
 */
@RestController
@AllArgsConstructor
@RequestMapping("/privilegevalue" )
@Api(value = "privilegevalue", tags = "权限键值表管理")
public class PrivilegeValueController {

    private final  PrivilegeValueService privilegeValueService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param privilegeValue 权限键值表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getPrivilegeValuePage(Page page, PrivilegeValue privilegeValue) {
        return R.ok(privilegeValueService.page(page, Wrappers.query(privilegeValue)));
    }


    /**
     * 全部查询
     * @return
     */
    @ApiOperation(value = "全部查询", notes = "全部查询")
    @GetMapping("/all" )
    public R getPrivilegeValueAll() {
        return R.ok(privilegeValueService.list());
    }


    /**
     * 通过id查询权限键值表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(privilegeValueService.getById(id));
    }

    /**
     * 新增权限键值表
     * @param privilegeValue 权限键值表
     * @return R
     */
    @ApiOperation(value = "新增权限键值表", notes = "新增权限键值表")
    @SysLog("新增权限键值表" )
    @PostMapping
    public R save(@RequestBody PrivilegeValue privilegeValue) {
        return R.ok(privilegeValueService.save(privilegeValue));
    }

    /**
     * 修改权限键值表
     * @param privilegeValue 权限键值表
     * @return R
     */
    @ApiOperation(value = "修改权限键值表", notes = "修改权限键值表")
    @SysLog("修改权限键值表" )
    @PutMapping
    public R updateById(@RequestBody PrivilegeValue privilegeValue) {
        return R.ok(privilegeValueService.updateById(privilegeValue));
    }

    /**
     * 通过id删除权限键值表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除权限键值表", notes = "通过id删除权限键值表")
    @SysLog("通过id删除权限键值表" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(privilegeValueService.removeById(id));
    }


    /**
     * 添加或者修改
     *
     * @param appPrivilegeValue 参数
     * @return
     */
    @ApiOperation(value = "通过id删除权限键值表", notes = "通过id删除权限键值表")
    @PostMapping(value = "/saveOrUpdate")
    public R<String> saveOrUpdate(@RequestBody PrivilegeValue appPrivilegeValue) {
        privilegeValueService.saveOrUpdate(appPrivilegeValue);
        return R.ok();
    }


}
