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

package com.laputa.iot.org.api.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工
 *
 * @author Sommer
 * @date 2021-09-30 15:39:05
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data

@ApiModel(value = "员工")
public class StaffVO  implements  Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 工号
     */
    @ApiModelProperty(value="工号")
    private String code;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名")
    private String name;

    /**
     * 岗位编码
     */
    @ApiModelProperty(value="岗位编码")
    private String positionCode;

    /**
     * 职级编码
     */
    @ApiModelProperty(value="职级编码")
    private String jobGradeCode;

    /**
     * 领导的工号
     */
    @ApiModelProperty(value="领导的工号")
    private String leaderCode;

    /**
     * 人员头像
     */
    @ApiModelProperty(value="人员头像")
    private String avatar;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 公司ID
     */
    @ApiModelProperty(value="公司ID")
    private Long companyId;

    /**
     * 公司名称
     */
    @ApiModelProperty(value="公司名称")
    private String companyName;

    /**
     * 部门id
     */
    @ApiModelProperty(value="部门id")
    private Long deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value="部门名称")
    private String deptName;

    /**
     * 状态（1：在职，0：离职）
     */
    @ApiModelProperty(value="状态（1：在职，0：离职）")
    private Integer status;

    /**
     * 性别1：男；2：女
     */
    @ApiModelProperty(value="性别1：男；2：女")
    private Integer sex;

    /**
     * 离职时间
     */
    @ApiModelProperty(value="离职时间")
    private LocalDateTime leaveDate;

    /**
     * 地址
     */
    @ApiModelProperty(value="地址")
    private String address;

    /**
     * 传真
     */
    @ApiModelProperty(value="传真")
    private String fax;

    /**
     * ddUserid
     */
    @ApiModelProperty(value="ddUserid")
    private String ddUserid;

    /**
     * 是否删除 0正常 1删除
     */
    @ApiModelProperty(value="是否删除 0正常 1删除")
    private Integer deleted;

    /**
     * 乐观锁
     */
    @ApiModelProperty(value="乐观锁")
    private Integer version;

    /**
     * 创建人id
     */
    @ApiModelProperty(value="创建人id")
    private String createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    @ApiModelProperty(value="更新人id")
    private String updateUser;

    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private LocalDateTime updateTime;

    /**
     * 所属租户
     */
    @ApiModelProperty(value="所属租户",hidden=true)
    private Long tenantId;




}
