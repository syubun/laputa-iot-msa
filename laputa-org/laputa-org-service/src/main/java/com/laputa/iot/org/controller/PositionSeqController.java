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
import com.laputa.iot.common.core.base.vo.CheckExistVo;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.api.entity.PositionSeq;
import com.laputa.iot.org.service.PositionSeqService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 岗位编码序列
 *
 * @author Sommer.Jiang
 * @date 2021-09-29 21:21:09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/positionseq" )
@Api(value = "positionseq", tags = "岗位编码序列管理")
public class PositionSeqController extends BaseController<PositionSeq> {

    private static final String MODULE_SN = "PositionSeq:";
    private final  PositionSeqService positionSeqService;
    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     * @return
     */
    @PostMapping(value = "/checkEntityExist", produces = "application/json")
    protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(positionSeqService, checkExistVo);
    }
    /**
     * 分页查询
     * @param page 分页对象
     * @param positionSeq 岗位编码序列
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission(MODULE_SN+'view')")
    public R getPositionSeqPage(Page page, PositionSeq positionSeq) {
        return R.ok(positionSeqService.page(page, Wrappers.query(positionSeq)));
    }
    /**
     * @return 岗位编码序列列表数组
     * @Description: 获取所有
     */
    @ApiOperation(value = "岗位编码序列列表数组", notes = "岗位编码序列列表数组")
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionSeq:view')") //只有角色是SYSTEM ADMIN两个才能操作
    @GetMapping("/getPositionSeqs")
    public R<List> getPositionSeqs(PositionSeq positionSeq) {

        LambdaQueryWrapper<PositionSeq> positionSeqLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(positionSeq.getKeyword())){
            positionSeqLambdaQueryWrapper.like(PositionSeq::getCode, positionSeq.getKeyword()).or()
                    .like(PositionSeq::getName, positionSeq.getKeyword());
        }
        positionSeqLambdaQueryWrapper.orderByAsc(PositionSeq::getOrderNo);
        List<PositionSeq> list = positionSeqService.list(positionSeqLambdaQueryWrapper);

        return R.ok(list);
    }

    /**
     * 通过id查询岗位编码序列
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionSeq:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(positionSeqService.getById(id));
    }

    /**
     * 新增岗位编码序列
     * @param positionSeq 岗位编码序列
     * @return R
     */
    @ApiOperation(value = "新增岗位编码序列", notes = "新增岗位编码序列")
    @SysLog("新增岗位编码序列" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionSeq:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody PositionSeq positionSeq) {
        return R.ok(positionSeqService.save(positionSeq));
    }

    /**
     * 添加或者修改
     *
     * @param positionSeq 参数
     * @return
     */
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionSeq:update')") //只有角色是SYSTEM ADMIN两个才能操作
    @PostMapping(value = "/saveOrUpdate")
    public R<Boolean> saveOrUpdate(@RequestBody PositionSeq positionSeq) {
        return R.ok(positionSeqService.saveOrUpdate(positionSeq));
    }
    /**
     * 修改岗位编码序列
     * @param positionSeq 岗位编码序列
     * @return R
     */
    @ApiOperation(value = "修改岗位编码序列", notes = "修改岗位编码序列")
    @SysLog("修改岗位编码序列" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionSeq:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody PositionSeq positionSeq) {
        return R.ok(positionSeqService.updateById(positionSeq));
    }

    /**
     * 通过id删除岗位编码序列
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除岗位编码序列", notes = "通过id删除岗位编码序列")
    @SysLog("通过id删除岗位编码序列" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionSeq:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R removeById(@PathVariable Long id) {
        return R.ok(positionSeqService.removeById(id));
    }

}
