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

package com.laputa.iot.upms.api.entity;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 社交账号处理
 *
 * @author sommer.jiang
 * @date 2021-07-24 09:16:46
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("sys_user_social")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "社交账号处理")
public class SysUserSocial extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;


    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String name;

    /**
     * 账号类型：wx微信登录Id mini小程序Id，qqQQ openId gitee_id码云 osc_id 开源中国 
     */
    @ApiModelProperty(value="账号类型：wx微信登录Id mini小程序Id，qqQQ openId gitee_id码云 osc_id 开源中国 ")
    private String type;

    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String avatar;

    /**
     * 社交登录UID
     */
    @ApiModelProperty(value="社交登录UID")
    private String socialUid;

    /**
     * 社交登录TOKEN
     */
    @ApiModelProperty(value="社交登录TOKEN")
    private String accessToken;

    /**
     * 社交登录过期时间
     */
    @ApiModelProperty(value="社交登录过期时间")
    private String expiresIn;



}
