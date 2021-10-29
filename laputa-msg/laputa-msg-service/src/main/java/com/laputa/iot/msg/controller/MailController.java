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

package com.laputa.iot.msg.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.msg.api.entity.Mail;
import com.laputa.iot.msg.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 邮件
 *
 * @author Sommer
 * @date 2021-10-20 10:13:09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mail" )
@Api(value = "mail", tags = "邮件管理")
public class MailController {

    private static final String MODULE_SN = "Mail:";
    private final  MailService mailService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param mail 邮件
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Mail:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getMailPage(Page page, Mail mail) {
        return R.ok(mailService.page(page, Wrappers.query(mail)));
    }


    /**
     * 通过id查询邮件
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Mail:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(mailService.getById(id));
    }

    /**
     * 新增邮件
     * @param mail 邮件
     * @return R
     */
    @ApiOperation(value = "新增邮件", notes = "新增邮件")
    @SysLog("新增邮件" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Mail:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody Mail mail) {
        return R.ok(mailService.save(mail));
    }

    /**
     * 修改邮件
     * @param mail 邮件
     * @return R
     */
    @ApiOperation(value = "修改邮件", notes = "修改邮件")
    @SysLog("修改邮件" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Mail:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody Mail mail) {
        return R.ok(mailService.updateById(mail));
    }

    /**
     * 通过id删除邮件
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除邮件", notes = "通过id删除邮件")
    @SysLog("通过id删除邮件" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('Mail:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R deleteById(@PathVariable Long id) {
        return R.ok(mailService.removeById(id));
    }

}
