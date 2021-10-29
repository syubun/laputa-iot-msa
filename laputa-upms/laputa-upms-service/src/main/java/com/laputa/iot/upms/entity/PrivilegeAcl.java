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
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.laputa.iot.common.core.base.entity.BaseEntity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;


/**
 * 模块权限表
 *
 * @author Sommer
 * @date 2021-09-08 12:48:31
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("sys_privilege_acl")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "模块权限表")
public class PrivilegeAcl extends BaseEntity<Long> {

    private static final long serialVersionUID = 7205739822827794614L;

    /**
     * 授权允许
     */
    public static final int ACL_YES = 1;

    /**
     * 授权不允许
     */
    public static final int ACL_NO = 0;

    /**
     * 授权不确定
     */
    public static final int ACL_NEUTRAL = -1;
    /**
     * 模块id
     */
    @ApiModelProperty(value = "类型 0为基础 1为权限 2为用户")
    private Integer type;

    /**
     * Roleid
     */

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    /**
     * roleSn
     */
    @ApiModelProperty(value = "releaseSn")
    private String roleSn;

    /**
     * 模块id
     */
    @ApiModelProperty(value = "模块id")
    private Long menuId;

    /**
     * 模块标示
     */
    @ApiModelProperty(value = "模块标示")
    private String menuSn;


    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**
     * 权限状态
     */
    @ApiModelProperty(value = "权限状态")
    private BigInteger aclState;

    /**
     * 备注
     */
    @Size(max = 200, message = "备注长度不能超过200")
    @ApiModelProperty(value = "备注")
    private String remark;
    // 临时变量-s
    @TableField(exist = false)
    private List<PrivilegeValue> pvs;

    @TableField(exist = false)
    private String pvsPermission;

    /**
     * 设置权限
     *
     * @param permission
     * @param yes
     */
    public void setAclValues(int permission, boolean yes) {
        if (aclState == null) {
            aclState = new BigInteger("0");
        }
        BigInteger temp = new BigInteger("1");
        temp = temp.shiftLeft(permission);
        if (yes) {
            aclState = aclState.or(temp);
        } else {
            aclState = aclState.xor(temp);
        }
    }


    /**
     * 设置权限
     *
     * @param
     * @param
     */
    public boolean setAclValueByPositions(List<Integer> positions) {
        if (CollectionUtils.isNotEmpty(positions)){
            aclState = (new BigInteger("0"));
            positions.forEach(position -> this.setAclValues(position, true));
            return true;
        }
      aclState = (new BigInteger("0"));
      return  false;
    }


    /**
     * 得到权限
     *
     * @param permission
     * @return
     */
    public int getPermission(int permission) {
        if (aclState == null) {
            aclState = new BigInteger("0");
        }
        BigInteger temp = new BigInteger("1");
        temp = temp.shiftLeft(permission);
        temp = temp.and(aclState);
        if (StringUtils.isNotBlank(temp.toString()) && temp.toString() != "0") {
            return ACL_YES;
        }
        return ACL_NO;
    }

    @Builder
    public PrivilegeAcl( Long roleId, String roleSn, Long menuId, String menuSn, Long userId, String userName, BigInteger aclState, String remark, int type) {

        this.roleId = roleId;
        this.roleSn = roleSn;
        this.menuId = menuId;
        this.menuSn = menuSn;
        this.userId = userId;
        this.userName = userName;
        this.aclState = aclState;
        this.remark = remark;
        this.type = type;

    }

}
