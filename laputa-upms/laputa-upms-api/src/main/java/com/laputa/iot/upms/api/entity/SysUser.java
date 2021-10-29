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


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.laputa.iot.common.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author sommer.jiang
 * @date 2021-07-23 13:03:26
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户表")
public class SysUser extends BaseEntity<Long> {


    private static final long serialVersionUID = -6950877265898716315L;
    /**
     * 用户账号
     */
    @ApiModelProperty(value="用户账号")
    @NotEmpty(message = "账号不能为空")
    private String username;

    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 加密盐
     */
    @ApiModelProperty(value="加密盐")
    private String salt;

    /**
     * 手机
     */
    @ApiModelProperty(value="手机")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String avatar;


    /**
     * 头像
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 社交登录UID
     */
    @ApiModelProperty(value="社交登录UID")
    private String socialUid;

    /**
     * 是否被锁 0启用 1禁用
     */
    @ApiModelProperty(value="是否被锁 0启用 1禁用")
    private String lockFlag;


    private Integer gender;

    @TableField(exist = false)
    private String role;

    @TableField(exist = false)
    private String authority;


    @Builder
    public SysUser(Long id, String username, String name, String password, String salt, String phone, String email, String avatar, String remark, String socialUid,  String lockFlag,  Integer version, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime, Long tenantId) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.socialUid = socialUid;
        this.remark = remark;
        this.lockFlag = lockFlag;
        this.version = version;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.tenantId = tenantId;
    }


    public String toNomalString() {
        return "SysUser{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                ", gender=" + gender +
                '}';
    }
}
