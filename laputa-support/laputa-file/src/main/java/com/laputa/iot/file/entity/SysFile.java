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

package com.laputa.iot.file.entity;


import com.laputa.iot.common.core.base.entity.BaseEntity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 文件管理
 *
 * @author Sommer
 * @date 2021-08-25 09:26:24
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("sys_file")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "文件管理")
public class SysFile extends BaseEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 文件名
     */
    @NotEmpty(message = "文件名不能为空")
    @Size(max = 128, message = "文件名长度不能超过128")
    @ApiModelProperty(value = "文件名")
    private String fileName;

    /**
     * 桶名
     */
    @NotEmpty(message = "桶名不能为空")
    @Size(max = 128, message = "桶名长度不能超过128")
    @ApiModelProperty(value = "桶名")
    private String bucketName;

    /**
     * 原始名
     */
    @NotEmpty(message = "原始名不能为空")
    @Size(max = 128, message = "原始名长度不能超过128,")
    @ApiModelProperty(value = "原始名")
    private String original;

    /**
     * 后缀名
     */
    @NotEmpty(message = "后缀名不能为空")
    @Size(max = 64, message = "后缀名长度不能超过64,")
    @ApiModelProperty(value = "后缀名")
    private String fileExten;

    /**
     * 文件大小
     */
    @NotEmpty(message = "文件大小不能为空")
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    /**
     * 快速链接
     */
    @NotEmpty(message = "快速链接不能为空")
    @Size(max = 1024, message = "快速链接长度不能超过1024,")
    @ApiModelProperty(value = "快速链接")
    private String url;

    /**
     * 类型
     */
    @NotEmpty(message = "类型不能为空")
    @Size(max = 64, message = "类型长度不能超过64,")
    @ApiModelProperty(value = "类型")
    private String contentType;

    /**
     * 是否图片
     */
    @ApiModelProperty(value = "是否图片")
    private Boolean isImg;

    /**
     * 来源 local minio s3 qiniu aliyun
     */
    @Size(max = 32, message = "来源 local minio s3 qiniu aliyun长度不能超过32,")
    @ApiModelProperty(value = "来源 local minio s3 qiniu aliyun")
    private String source;


}
