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

package com.laputa.iot.file.api.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

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

@ApiModel(value = "文件管理")
public class FileVO  implements  Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 文件名
     */
    @ApiModelProperty(value="文件名")
    private String fileName;

    /**
     * 桶名
     */
    @ApiModelProperty(value="桶名")
    private String bucketName;

    /**
     * 原始名
     */
    @ApiModelProperty(value="原始名")
    private String original;

    /**
     * 后缀名
     */
    @ApiModelProperty(value="后缀名")
    private String fileExten;

    /**
     * 文件大小
     */
    @ApiModelProperty(value="文件大小")
    private Long fileSize;

    /**
     * 快速链接
     */
    @ApiModelProperty(value="快速链接")
    private String url;

    /**
     * 类型
     */
    @ApiModelProperty(value="类型")
    private String contentType;

    /**
     * 是否图片
     */
    @ApiModelProperty(value="是否图片")
    private boolean isImg;

    /**
     * 来源 local minio s3 qiniu aliyun
     */
    @ApiModelProperty(value="来源 local minio s3 qiniu aliyun")
    private String source;

    /**
     * 上传时间
     */
    @ApiModelProperty(value="上传时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @ApiModelProperty(value="逻辑删除")
    private Integer deleted;

    /**
     * 所属租户
     */
    @ApiModelProperty(value="所属租户",hidden=true)
    private Long tenantId;

    /**
     * 版本号
     */
    @ApiModelProperty(value="版本号")
    private Integer version;

    /**
     * 创建者
     */
    @ApiModelProperty(value="创建者")
    private Long createUser;

    /**
     * 更新者
     */
    @ApiModelProperty(value="更新者")
    private Long updateUser;




}
