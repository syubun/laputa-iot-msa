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


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.BaseController;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.base.vo.CheckExistVo;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.api.entity.Role;
import com.laputa.iot.org.api.entity.StaffRole;
import com.laputa.iot.org.api.vo.RoleStaffVo;
import com.laputa.iot.org.service.RoleService;

import com.laputa.iot.org.service.StaffRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 公司角色
 *
 * @author Sommer
 * @date 2021-09-29 22:08:34
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role" )
@Api(value = "role", tags = "公司角色管理")
public class RoleController extends BaseController<Role> {

    private static final String MODULE_SN = "Role:";

    private final  RoleService roleService;

    @Autowired
    StaffRoleService staffRoleService;


    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     * @return
     */
    @PostMapping(value = "/checkEntityExist")
    protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(roleService, checkExistVo);
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param role 公司角色
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getRolePage(Page page, Role role) {
        return R.ok(roleService.getPageModelByQuery(page, role,0L));
    }

    /**
     * 获取角色的人员列表
     *
     * @param roleId       角色Id
     * @param staffRole 人员
     * @return
     */
    @ApiOperation(value = "获取角色的人员列表", notes = "获取角色的人员列表")
    @PostMapping(value = "/staffsByRole/{roleId}")
    public R<List> getStaffByRole(@PathVariable Long roleId, @RequestBody StaffRole staffRole) {

        staffRole.setRoleId(roleId);
        List<RoleStaffVo> rolePersonalVos = new ArrayList<>();
        if (roleId!=null && roleId != 0L){
            rolePersonalVos = staffRoleService.getRoleStaffs(staffRole);
        }
        return R.ok(rolePersonalVos);
    }
    /**
     * 通过id查询公司角色
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )

    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(roleService.getById(id));
    }

    /**
     * 新增公司角色
     * @param role 公司角色
     * @return R
     */
    @ApiOperation(value = "新增公司角色", notes = "新增公司角色")
    @SysLog("新增公司角色" )
    @PostMapping

    public R save(@RequestBody Role role) {
        return R.ok(roleService.save(role));
    }

    /**
     * 修改公司角色
     * @param role 公司角色
     * @return R
     */
    @ApiOperation(value = "修改公司角色", notes = "修改公司角色")
    @SysLog("修改公司角色" )
    @PutMapping

    public R updateById(@RequestBody Role role) {
        return R.ok(roleService.updateById(role));
    }

    /**
     * 通过id删除公司角色
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除公司角色", notes = "通过id删除公司角色")
    @SysLog("通过id删除公司角色" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable Long id) {
        return R.ok(roleService.removeById(id));
    }



    /**
     * 添加或者修改
     *
     * @param role 参数
     * @return
     */
    @ApiOperation(value = "添加或者更新公司组织角色", notes = "添加或者更新公司组织角色")
    @SysLog("添加或者更新公司组织角色" )
    @PostMapping(value = "/saveOrUpdate")
    public R<Boolean> saveOrUpdate(@RequestBody Role role) {
        return  R.ok(roleService.saveOrUpdate(role));
    }

}
