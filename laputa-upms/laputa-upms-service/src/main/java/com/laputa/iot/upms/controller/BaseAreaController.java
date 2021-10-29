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
import com.laputa.iot.upms.entity.BaseArea;
import com.laputa.iot.upms.service.BaseAreaService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 区域信息
 *
 * @author Sommer
 * @date 2021-09-22 09:14:19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/basearea" )
@Api(value = "basearea", tags = "区域信息管理")
public class BaseAreaController {

    private static final String MODULE_SN = "BaseArea:";
    private final  BaseAreaService baseAreaService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param baseArea 区域信息
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("@pms.hasPermission('BaseArea:view')")
    public R getBaseAreaPage(Page page, BaseArea baseArea) {
        return R.ok(baseAreaService.page(page, Wrappers.query(baseArea)));
    }


    /**
     * 通过id查询区域信息
     * @param code id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{code}" )
    @PreAuthorize("@pms.hasPermission('BaseArea:view')" )
    public R getById(@PathVariable("code" ) String code) {
        return R.ok(baseAreaService.getById(code));
    }

    /**
     * 新增区域信息
     * @param baseArea 区域信息
     * @return R
     */
    @ApiOperation(value = "新增区域信息", notes = "新增区域信息")
    @SysLog("新增区域信息" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('upms_basearea_add')" )
    public R save(@RequestBody BaseArea baseArea) {
        return R.ok(baseAreaService.save(baseArea));
    }

    /**
     * 修改区域信息
     * @param baseArea 区域信息
     * @return R
     */
    @ApiOperation(value = "修改区域信息", notes = "修改区域信息")
    @SysLog("修改区域信息" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('upms_basearea_edit')" )
    public R updateById(@RequestBody BaseArea baseArea) {
        return R.ok(baseAreaService.updateById(baseArea));
    }

    /**
     * 通过id删除区域信息
     * @param code id
     * @return R
     */
    @ApiOperation(value = "通过id删除区域信息", notes = "通过id删除区域信息")
    @SysLog("通过id删除区域信息" )
    @DeleteMapping("/{code}" )
    @PreAuthorize("@pms.hasPermission('upms_basearea_del')" )
    public R removeById(@PathVariable String code) {
        return R.ok(baseAreaService.removeById(code));
    }

}
