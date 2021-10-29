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

package com.laputa.iot.org.api.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.laputa.iot.common.core.base.entity.BaseEntity;

import com.baomidou.mybatisplus.annotation.TableName;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 公司角色
 *
 * @author Sommer
 * @date 2021-09-29 22:08:34
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("org_role")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "公司角色")
public class Role extends BaseEntity<Long>{




    /**
     * 公司id
     */


    @ApiModelProperty(value="公司id")
    private Long companyId;

    /**
     * 职位id
     */


    @ApiModelProperty(value="职位id")
    private Long positionId;

    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空")
    @Size(max = 64, message = "名称长度不能超过64")
    @ApiModelProperty(value="名称")
    private String name;

    /**
     * 标识
     */
    @NotEmpty(message = "标识不能为空")
    @Size(max = 64, message = "标识长度不能超过64")
    @ApiModelProperty(value="标识")
    private String sn;

    /**
     * 备注
     */
    @NotEmpty(message = "备注不能为空")
    @Size(max = 1024, message = "备注长度不能超过1024")
    @ApiModelProperty(value="备注")
    private String note;

    /**
     * type
     */
    @NotEmpty(message = "type不能为空")

    @ApiModelProperty(value="type")
    private Integer type;

    /**
     * orderNo
     */
    @NotEmpty(message = "orderNo不能为空")

    @ApiModelProperty(value="orderNo")
    private Integer orderNo;



    @TableField(exist = false)
    private String companyName;
    @TableField(exist = false)
    private Long staffId;
    @TableField(exist = false)
    private List<Company> companies;
    @TableField(exist = false)
    private List<Personal> personals;



}
