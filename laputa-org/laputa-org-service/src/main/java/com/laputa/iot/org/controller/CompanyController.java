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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.BaseController;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.base.vo.CheckExistVo;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.org.api.entity.Company;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.service.CompanyService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 公司表
 *
 * @author Sommer
 * @date 2021-09-30 14:10:33
 */
@RestController
@AllArgsConstructor
@RequestMapping("/company" )
@Api(value = "company", tags = "公司表管理")
@Slf4j
public class CompanyController extends BaseController<Company> {

    private static final String MODULE_SN = "Company:";
    private final  CompanyService companyService;


    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     * @return
     */
    @PostMapping(value = "/checkEntityExist")
    protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(companyService, checkExistVo);
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param company 公司表
     * @return
     */
    @SneakyThrows
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
//    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getCompanyPage(Page page, Company company) {
//        LambdaQueryWrapper<Company> wrapper = Wrappers.lambdaQuery();

        return R.ok(companyService.getCompanysByPage(page, company));
    }


    /**
     * 通过id查询公司表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )

    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(companyService.getById(id));
    }

    /**
     * 新增公司表
     * @param company 公司表
     * @return R
     */
    @ApiOperation(value = "新增公司表", notes = "新增公司表")
    @SysLog("新增公司表" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Company:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody Company company) {
        return R.ok(companyService.save(company));
    }

    /**
     * 添加或者修改
     *
     * @param company 参数
     * @return
     */
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Company:add')") //只有角色是SYSTEM ADMIN两个才能操作
    @PostMapping(value = "/saveOrUpdate")
    public R<Boolean> saveOrUpdate(@RequestBody Company company) {
        return R.ok( companyService.saveOrUpdate(company));
    }
    /**
     * 修改公司表
     * @param company 公司表
     * @return R
     */
    @ApiOperation(value = "修改公司表", notes = "修改公司表")
    @SysLog("修改公司表" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Company:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody Company company) {
        return R.ok(companyService.updateById(company));
    }

    /**
     * 通过id删除公司表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除公司表", notes = "通过id删除公司表")
    @SysLog("通过id删除公司表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Company:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R removeById(@PathVariable Long id) {
        return R.ok(companyService.removeById(id));
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Company:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    @PostMapping(value = "/delete", produces = "application/json")
    public R<Boolean> delete(@RequestBody List<Long> ids) {

        return companyService.deleteByIds(ids);
    }


}
