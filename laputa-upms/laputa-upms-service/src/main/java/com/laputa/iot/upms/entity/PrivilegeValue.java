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

package com.laputa.iot.upms.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.laputa.iot.common.core.base.entity.BaseEntity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;


/**
 * 权限键值表
 *
 * @author Sommer
 * @date 2021-09-08 12:57:42
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("sys_privilege_value")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "权限键值表")
public class PrivilegeValue extends BaseEntity<Long>{


    private static final long serialVersionUID = -3059185711476731817L;
    /**
     * 整型的位
     */
    @ApiModelProperty(value = "整型的位")
    private Integer position;

    /**
     * 名称
     */
    @Size(max = 32, message = "名称长度不能超过32")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 排序号
     */
    @NotEmpty(message = "排序号不能为空")
    @ApiModelProperty(value = "排序号")
    private Integer orderNo;

    /**
     * 权限图标
     */
    @NotEmpty(message = "权限图标不能为空")
    @Size(max = 64, message = "权限图标长度不能超过64")
    @ApiModelProperty(value = "权限图标")
    private String icon;


    /**
     * 操作符
     */
    @ApiModelProperty(value = "操作符")
    private String operation;

    /**
     * 缩写
     */
    @ApiModelProperty(value = "缩写")
    private String abbr;

    /**
     * 备注
     */
    @Size(max = 200, message = "备注长度不能超过200")
    @ApiModelProperty(value = "备注")
    private String remark;

    // 临时变量
    @TableField(exist = false)
    private BigInteger state;
    //模块id  临时变量
    @TableField(exist = false)
    private Long menuId;
    //标记是否选中
    @TableField(exist = false)
    private boolean flag = false;


    // 临时变量
    @TableField(exist = false)
    private String permissionName;

}
