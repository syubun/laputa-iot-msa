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
import com.laputa.iot.common.core.base.BaseController;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.api.entity.JobGrade;
import com.laputa.iot.org.api.vo.OrgTreeVo;
import com.laputa.iot.org.service.JobGradeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 工作等级表
 *
 * @author Sommer
 * @date 2021-10-03 11:35:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/jobgrade" )
@Api(value = "jobgrade", tags = "工作等级表管理")
public class JobGradeController extends BaseController<JobGrade> {

    private static final String MODULE_SN = "JobGrade:";
    private final  JobGradeService jobGradeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param jobGrade 工作等级表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGrade:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getJobGradePage(Page page, JobGrade jobGrade) {
        return R.ok(jobGradeService.page(page, Wrappers.query(jobGrade)));
    }


    /**
     * @return 获取职级树
     */
    @ApiOperation(value = "获取职级树", notes = "获取职级树")
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGrade:view')") //只有角色是SYSTEM ADMIN两个才能操作
    @GetMapping("/getJobGradeTree")
    public R<List> getJobGradeTree() {
        List<OrgTreeVo> jobGradeTree = jobGradeService.getJobGradeTree();

        return R.ok(jobGradeTree);
    }


    /**
     * @return 职级列表数组字符串
     * @Description: 获取所有职级
     */
    @ApiOperation(value = "职级列表数组字符串", notes = "职级列表数组字符串")
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGrade:view')") //只有角色是SYSTEM ADMIN两个才能操作
    @GetMapping("/getJobGrades")
    public R<List> getJobGrades(JobGrade jobGrade) {
        List<JobGrade> list =  jobGradeService.getJobGrades(jobGrade);
        return R.ok(list);
    }


    /**
     * 通过id查询工作等级表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGrade:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(jobGradeService.getById(id));
    }

    /**
     * 新增工作等级表
     * @param jobGrade 工作等级表
     * @return R
     */
    @ApiOperation(value = "新增工作等级表", notes = "新增工作等级表")
    @SysLog("新增工作等级表" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGrade:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody JobGrade jobGrade) {
        return R.ok(jobGradeService.save(jobGrade));
    }

    /**
     * 修改工作等级表
     * @param jobGrade 工作等级表
     * @return R
     */
    @ApiOperation(value = "修改工作等级表", notes = "修改工作等级表")
    @SysLog("修改工作等级表" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGrade:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody JobGrade jobGrade) {
        return R.ok(jobGradeService.updateById(jobGrade));
    }

    /**
     * 通过id删除工作等级表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除工作等级表", notes = "通过id删除工作等级表")
    @SysLog("通过id删除工作等级表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGrade:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R deleteById(@PathVariable Long id) {
        return R.ok(jobGradeService.removeById(id));
    }

}
