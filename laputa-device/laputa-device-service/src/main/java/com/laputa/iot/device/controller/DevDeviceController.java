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

package com.laputa.iot.device.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.device.entity.DevDevice;
import com.laputa.iot.device.service.DevDeviceService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author Laputa IOT code generator
 * @date 2021-07-10 08:25:48
 */
@RestController
@AllArgsConstructor
@RequestMapping("/devdevice" )
@Api(value = "devdevice", tags = "管理")
public class DevDeviceController {

    private final  DevDeviceService devDeviceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param devDevice 
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('generator_devdevice_view')" )
    public R getDevDevicePage(Page page, DevDevice devDevice) {
        return R.ok(devDeviceService.page(page, Wrappers.query(devDevice)));
    }


    /**
     * 通过id查询
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_devdevice_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(devDeviceService.getById(id));
    }

    /**
     * 新增
     * @param devDevice 
     * @return R
     */
    @ApiOperation(value = "新增", notes = "新增")
    @SysLog("新增" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_devdevice_add')" )
    public R save(@RequestBody DevDevice devDevice) {
        return R.ok(devDeviceService.save(devDevice));
    }

    /**
     * 修改
     * @param devDevice 
     * @return R
     */
    @ApiOperation(value = "修改", notes = "修改")
    @SysLog("修改" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_devdevice_edit')" )
    public R updateById(@RequestBody DevDevice devDevice) {
        return R.ok(devDeviceService.updateById(devDevice));
    }

    /**
     * 通过id删除
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除", notes = "通过id删除")
    @SysLog("通过id删除" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('generator_devdevice_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(devDeviceService.removeById(id));
    }

}
