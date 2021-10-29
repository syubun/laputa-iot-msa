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

package com.laputa.iot.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * 
 *
 * @author sommer.jiang
 * @date 2021-07-10 08:25:48
 */
@ToString(callSuper = true)
@Data
@TableName("dev_device")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DevDevice",description = "设备")
public class DevDevice extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备名
     */
    @ApiModelProperty(value="设备名")
    private String deviceName;

    /**
     * 机器码
     */
    @ApiModelProperty(value="机器码")
    private String deviceCode;

    /**
     * 机器型号
     */
    @ApiModelProperty(value="机器型号")
    private String deviceType;

    /**
     * 机器使用状态
     */
    @ApiModelProperty(value="机器使用状态")
    private Integer deviceStatus;

    /**
     * 固定二维码
     */
    @ApiModelProperty(value="固定二维码")
    private String deviceQrcode;

    /**
     * 地址
     */
    @ApiModelProperty(value="地址")
    private String address;



    /**
     * 机器图片
     */
    @ApiModelProperty(value="机器图片")
    private String pic;


    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String description;

    /**
     * 经度
     */
    @ApiModelProperty(value="经度")
    private Double longitude;

    /**
     * 维度
     */
    @ApiModelProperty(value="维度")
    private Double latitude;

}
