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
import com.laputa.iot.common.core.constant.PermissionConatant;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.api.entity.PositionInfo;
import com.laputa.iot.org.api.entity.PositionSeq;
import com.laputa.iot.org.api.vo.OrgTreeVo;
import com.laputa.iot.org.service.PositionInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 岗位
 *
 * @author Sommer
 * @date 2021-10-03 12:04:34
 */
@RestController
@AllArgsConstructor
@RequestMapping("/positionInfo" )
@Api(value = "positioninfo", tags = "岗位管理")
public class PositionInfoController extends BaseController<PositionInfo> {

    private static final String MODULE_SN = "PositionInfo:";
    private final  PositionInfoService positionInfoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param positionInfo 岗位
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionInfo:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getPositionInfoPage(Page page, PositionInfo positionInfo) {
        LambdaQueryWrapper<PositionInfo> positionInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(positionInfo.getKeyword())){
            positionInfoLambdaQueryWrapper.like(PositionInfo::getCode, positionInfo.getKeyword()).or()
                    .like(PositionInfo::getName, positionInfo.getKeyword());
        }

        if (positionInfo.getPositionSeqId()!=null&& positionInfo.getPositionSeqId()!=0L){
            positionInfoLambdaQueryWrapper.eq(PositionInfo::getPositionSeqId, positionInfo.getPositionSeqId());
        }

        positionInfoLambdaQueryWrapper.orderByAsc(PositionInfo::getOrderNo);
        return R.ok(positionInfoService.page(page, positionInfoLambdaQueryWrapper));
    }

    /**
     * 判断字段是否存在
     *
     * @param checkExistVo 参数
     * @return
     */

    @PostMapping(value = "/checkEntityExist")
    protected R<Boolean> checkEntityExist(@RequestBody CheckExistVo checkExistVo) {
        return this.checkExist(positionInfoService, checkExistVo);
    }

    /**
     * @return 获取岗位树
     */

    @GetMapping("/getPositionInfoTree")
    @ApiOperation(value = "获取岗位树", notes = "获取岗位树")
    public R<List> getPositionInfoTree() {

        List<OrgTreeVo> positionTree = positionInfoService.getPositionTree();

        return R.ok(positionTree);
    }


    /**
     * 通过id查询岗位
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionInfo:view')") //只有角色是SYSTEM ADMIN两个才能操作
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(positionInfoService.getById(id));
    }

    /**
     * 新增岗位
     * @param positionInfo 岗位
     * @return R
     */
    @ApiOperation(value = "新增岗位", notes = "新增岗位")
    @SysLog("新增岗位" )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionInfo:add')") //只有角色是SYSTEM ADMIN两个才能操作
    public R save(@RequestBody PositionInfo positionInfo) {
        return R.ok(positionInfoService.save(positionInfo));
    }

    /**
     * 修改岗位
     * @param positionInfo 岗位
     * @return R
     */
    @ApiOperation(value = "修改岗位", notes = "修改岗位")
    @SysLog("修改岗位" )
    @PutMapping
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionInfo:update')") //只有角色是SYSTEM ADMIN两个才能操作
    public R updateById(@RequestBody PositionInfo positionInfo) {
        return R.ok(positionInfoService.updateById(positionInfo));
    }

    /**
     * 通过id删除岗位
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除岗位", notes = "通过id删除岗位")
    @SysLog("通过id删除岗位" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN') or @pms.hasPermission('PositionInfo:delete')") //只有角色是SYSTEM ADMIN两个才能操作
    public R deleteById(@PathVariable Long id) {
        return R.ok(positionInfoService.removeById(id));
    }

}
