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

package com.laputa.iot.org.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.BaseController;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.base.vo.CheckExistVo;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.common.security.annotation.Inner;
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.org.service.StaffService;

import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 员工
 *
 * @author Sommer
 * @date 2021-09-30 15:39:05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staff" )
@Api(value = "staff", tags = "员工管理")
public class StaffController extends BaseController<Staff> {

    private static final String MODULE_SN = "Staff:";
    private final  StaffService staffService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staff 员工
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Staff:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getStaffPage(Page page, Staff staff) {
        return R.ok(staffService.page(page, Wrappers.query(staff)));
    }

    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     * @return
     */
    @PostMapping(value = "/checkEntityExist", produces = "application/json")
    protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(staffService, checkExistVo);
    }

    /**
     * 分页获取列表
     *
     * @param page 查询参数 、 分页参数
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/getPageShowRoles" )
    public R getPagerModel(@RequestParam boolean showRoles, Page page, Staff staff) {

        R returnVo = staffService.getPagerModelByWrapper(page, staff, showRoles);

        return returnVo;
    }

    /**
     * 通过id查询员工
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
//    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Staff:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffService.getById(id));
    }


    /**
     * 通过id查询员工
     * @param
     * @return R
     */
    @Inner(false)
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/all" )
//    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Staff:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R<List<Staff>> getAllStaff() {
        return R.ok(staffService.list());
    }


    /**
     * 新增员工
     * @param staff 员工
     * @return R
     */
    @ApiOperation(value = "新增员工", notes = "新增员工")
    @SysLog("新增员工" )
    @PostMapping
//    @PreAuthorize("hasRole(T(com.bs.dmsbox.api.constants.RoleConstants).ROLE_AGENT) " +
//            "|| hasRole(T(com.bs.dmsbox.api.constants.RoleConstants).ROLE_ADMIN)" +
//            "|| (hasRole(T(com.bs.dmsbox.api.constants.RoleConstants).ROLE_CUSTOMER) && #userId == principal.username)")

    @PreAuthorize("hasAnyRole('ROLE_SYSTEM,ROLE_ADMIN')  or @pms.hasPermission('" + MODULE_SN+ "add" + "')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody Staff staff) {
        return R.ok(staffService.save(staff));
    }

    /**
     * 修改员工
     * @param staff 员工
     * @return R
     */
    @ApiOperation(value = "修改员工", notes = "修改员工")
    @SysLog("修改员工" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Staff:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody Staff staff) {
        return R.ok(staffService.updateById(staff));
    }

    /**
     * 通过id删除员工
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工", notes = "通过id删除员工")
    @SysLog("通过id删除员工" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Staff:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R deleteById(@PathVariable Long id) {
        return R.ok(staffService.removeById(id));
    }



    /**
     * 添加或者修改
     *
     * @param staff 参数
     * @return
     */
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Staff:update')") //只有角色是SYSTEM ADMIN两个才能操作
    @PostMapping(value = "/saveOrUpdate")
    public R<Boolean> saveOrUpdate(@RequestBody Staff staff) {
        boolean b = staffService.saveOrUpdateStaff(staff);
        return R.ok(b);
    }



    /**
     * 设置领导
     *
     * @param leaderCode 直属领导code
     * @param id         id
     * @return
     */
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Staff:update')") //只有角色是SYSTEM ADMIN两个才能操作
    @PostMapping(value = "/setLeaderCode/{leaderCode}/{id}")
    public R<Boolean> setLeaderCode(@PathVariable String leaderCode, @PathVariable String id) {
        LambdaUpdateWrapper<Staff> staffLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        staffLambdaUpdateWrapper.set(Staff::getLeaderCode, leaderCode).eq(Staff::getId, id);
        return R.ok( staffService.update(staffLambdaUpdateWrapper));
    }

}
