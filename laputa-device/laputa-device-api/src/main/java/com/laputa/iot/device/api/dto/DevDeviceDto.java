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

package com.laputa.iot.device.api.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 
 *
 * @author sommer.jiang
 * @date 2021-07-10 08:25:48
 */
@Data
@ApiModel(value = "DevDeviceDto")
public class DevDeviceDto {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value="主键")
    private Long id;

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
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private LocalDateTime updateTime;

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
