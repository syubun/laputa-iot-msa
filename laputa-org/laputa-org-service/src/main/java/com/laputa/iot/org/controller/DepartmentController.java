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
import com.laputa.iot.org.api.entity.Company;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.service.DepartmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 部门表
 *
 * @author Sommer.jiang
 * @date 2021-10-03 10:45:58
 */
@RestController
@AllArgsConstructor
@RequestMapping("/department" )
@Api(value = "department", tags = "部门表管理")
public class DepartmentController extends BaseController<Department> {

    private static final String MODULE_SN = "Department:";
    private final  DepartmentService departmentService;

    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     * @return
     */
    @PostMapping(value = "/checkEntityExist")
    protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(departmentService, checkExistVo);
    }

    /**
     * 根据条件获取 - 不分页
     *
     * @param department 查询参数
     * @return
     */
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Department:view')") //只有角色是SYSTEM ADMIN两个才能操作
    @GetMapping(value = "/getAll")
    public R<List<Department>> getAll(Department department) {
        List<Department> list = departmentService.getAll(department);
        return R.ok(list);
    }
    /**
     * 分页查询
     * @param page 分页对象
     * @param department 部门表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Department:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getDepartmentPage(Page page, Department department) {
        return R.ok(departmentService.page(page, Wrappers.query(department)));
    }

    /**
     * 获取组织树
     *
     * @return
     */
    @GetMapping(value = "/getOrgTree")
    public R<List> getOrgTree() {

        return R.ok(departmentService.getOrgTree());
    }


    /**
     * 通过id查询部门表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Department:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(departmentService.getById(id));
    }

    /**
     * 新增部门表
     * @param department 部门表
     * @return R
     */
    @ApiOperation(value = "新增部门表", notes = "新增部门表")
    @SysLog("新增部门表" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Department:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody Department department) {
        return R.ok(departmentService.save(department));
    }

    /**
     * 修改部门表
     * @param department 部门表
     * @return R
     */
    @ApiOperation(value = "修改部门表", notes = "修改部门表")
    @SysLog("修改部门表" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Department:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody Department department) {
        return R.ok(departmentService.updateById(department));
    }

    /**
     * 通过id删除部门表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除部门表", notes = "通过id删除部门表")
    @SysLog("通过id删除部门表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Department:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R deleteById(@PathVariable Long id) {
        return R.ok(departmentService.removeById(id));
    }



    /**
     * 添加或者修改
     *
     * @param department 参数
     * @return
     */
    @SysLog("更新或者创建部门表" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Company:add, Company:update')") //只有角色是SYSTEM ADMIN两个才能操作
    @PostMapping(value = "/saveOrUpdate")
    public R<Boolean> saveOrUpdate(@RequestBody Department department) {
        R<Boolean> result =  departmentService.saveOrUpdateDepartment(department);
        return result;
    }

}
