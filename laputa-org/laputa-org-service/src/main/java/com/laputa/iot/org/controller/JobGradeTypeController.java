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
import com.laputa.iot.org.api.entity.JobGradeType;
import com.laputa.iot.org.service.JobGradeTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 职级分类
 *
 * @author Sommer
 * @date 2021-10-03 11:11:22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/jobGradeType" )
@Api(value = "jobGradeType", tags = "职级分类管理")
public class JobGradeTypeController extends BaseController<JobGradeType> {

    private static final String MODULE_SN = "JobGradeType:";

    private final  JobGradeTypeService jobGradeTypeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param jobGradeType 职级分类
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGradeType:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getJobGradeTypePage(Page page, JobGradeType jobGradeType) {
        return R.ok(jobGradeTypeService.page(page, Wrappers.query(jobGradeType)));
    }


    /**
     * 职级分类列表数组字符串
     * @return
     */
    @ApiOperation(value = "职级分类列表数组字符串", notes = "职级分类列表json数组字符串")
    @GetMapping("/getJobGradeTypes" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGradeType:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getJobGradeTypes(JobGradeType jobGradeType) {
        LambdaQueryWrapper<JobGradeType> jobGradeTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(jobGradeType.getKeyword())){
            jobGradeTypeLambdaQueryWrapper.like(JobGradeType::getCode, jobGradeType.getKeyword()).or()
                    .like(JobGradeType::getName, jobGradeType.getKeyword());
        }
        if (jobGradeType.getCompanyId()!=null && !jobGradeType.getCompanyId().equals(0L)){
            jobGradeTypeLambdaQueryWrapper.eq(JobGradeType::getCompanyId, jobGradeType.getCompanyId());
        }
        jobGradeTypeLambdaQueryWrapper.orderByAsc(JobGradeType::getOrderNo);
        return R.ok(jobGradeTypeService.list());
    }


    /**
     * 通过id查询职级分类
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGradeType:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(jobGradeTypeService.getById(id));
    }

    /**
     * 新增职级分类
     * @param jobGradeType 职级分类
     * @return R
     */
    @ApiOperation(value = "新增职级分类", notes = "新增职级分类")
    @SysLog("新增职级分类" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGradeType:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody JobGradeType jobGradeType) {
        return R.ok(jobGradeTypeService.save(jobGradeType));
    }

    /**
     * 修改职级分类
     * @param jobGradeType 职级分类
     * @return R
     */
    @ApiOperation(value = "修改职级分类", notes = "修改职级分类")
    @SysLog("修改职级分类" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGradeType:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody JobGradeType jobGradeType) {
        return R.ok(jobGradeTypeService.updateById(jobGradeType));
    }

    /**
     * 通过id删除职级分类
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除职级分类", notes = "通过id删除职级分类")
    @SysLog("通过id删除职级分类" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('JobGradeType:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R deleteById(@PathVariable Long id) {
        return R.ok(jobGradeTypeService.removeById(id));
    }

}
