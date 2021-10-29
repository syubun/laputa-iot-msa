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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.org.api.entity.Role;
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.org.api.entity.StaffRole;
import com.laputa.iot.org.service.StaffRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 员工角色
 *
 * @author Sommer
 * @date 2021-10-10 13:16:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staffRole" )
@Api(value = "staffRole", tags = "员工角色管理")
public class StaffRoleController {

    private static final String MODULE_SN = "StaffRole:";
    private final  StaffRoleService staffRoleService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param staffRole 员工角色
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('StaffRole:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getStaffRolePage(Page page, StaffRole staffRole) {
        return R.ok(staffRoleService.page(page, Wrappers.query(staffRole)));
    }


    /**
     * 通过id查询员工角色
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('StaffRole:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(staffRoleService.getById(id));
    }

    /**
     * 新增员工角色
     * @param staffRole 员工角色
     * @return R
     */
    @ApiOperation(value = "新增员工角色", notes = "新增员工角色")
    @SysLog("新增员工角色" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('StaffRole:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody StaffRole staffRole) {
        return R.ok(staffRoleService.save(staffRole));
    }

    /**
     * 修改员工角色
     * @param staffRole 员工角色
     * @return R
     */
    @ApiOperation(value = "修改员工角色", notes = "修改员工角色")
    @SysLog("修改员工角色" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('StaffRole:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody StaffRole staffRole) {
        return R.ok(staffRoleService.updateById(staffRole));
    }

    /**
     * 通过id删除员工角色
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除员工角色", notes = "通过id删除员工角色")
    @SysLog("通过id删除员工角色" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('StaffRole:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R deleteById(@PathVariable Long id) {
        return R.ok(staffRoleService.removeById(id));
    }


    /**
     * 给用户分配角色
     *
     * @param roles      人员角色
     * @param staffId 人员id
     * @return
     */
    @ApiOperation(value = "给用户分配角色")
    @PostMapping(value = "/allocationRoles/{staffId}")
    public R<Boolean> allocationRoles(@PathVariable Long staffId, @RequestBody List<Role> roles) {

        return R.ok(staffRoleService.addStaffRolesByStaff(staffId, roles));
    }

    /**
     * 给角色分配用户
     *
     * @param roleId    角色Id
     * @param staffs 人员列表
     * @return
     */
    @ApiOperation(value = "给角色分配用户")
    @SysLog("给角色分配用户" )
    @PostMapping(value = "/allocationStaffs/{roleId}")
    public R<Boolean> allocationPersonals(@PathVariable Long roleId, @RequestBody List<Staff> staffs) {

        staffRoleService.addStaffRolesByRole(roleId, staffs);
        return R.ok();
    }
    /**
     * 删除人员的拥有的角色
     *
     * @param staffRole StaffRole
     * @return
     */
    @ApiOperation(value = "删除人员的拥有的角色")
    @SysLog("删除人员的拥有的角色" )
    @PostMapping(value = "/deletePersonalRole")
    public R<Boolean> deletePersonalRole(@RequestBody StaffRole staffRole) {

        LambdaQueryWrapper<StaffRole> personalRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        personalRoleLambdaQueryWrapper.eq(StaffRole::getStaffId, staffRole.getStaffId())
                .eq(StaffRole::getRoleId, staffRole.getRoleId());
        staffRoleService.remove(personalRoleLambdaQueryWrapper);
        return R.ok();
    }


}
