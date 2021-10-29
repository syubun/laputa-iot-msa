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
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
@TableName("org_staff")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "员工")
public class Staff extends BaseEntity<Long> {


        
    /**
     * 工号
     */
    @NotEmpty(message = "工号不能为空") 
    @Size(max = 32, message = "工号长度不能超过32") 
    @ApiModelProperty(value="工号")
    private String code;
        
    /**
     * 真实姓名
     */
    @NotEmpty(message = "真实姓名不能为空") 
    @Size(max = 20, message = "真实姓名长度不能超过20") 
    @ApiModelProperty(value="真实姓名")
    private String name;
        
    /**
     * 岗位编码
     */
    @NotEmpty(message = "岗位编码不能为空") 
    @Size(max = 80, message = "岗位编码长度不能超过80") 
    @ApiModelProperty(value="岗位编码")
    private String positionCode;
        
    /**
     * 职级编码
     */
    @NotEmpty(message = "职级编码不能为空") 
    @Size(max = 80, message = "职级编码长度不能超过80") 
    @ApiModelProperty(value="职级编码")
    private String jobGradeCode;
        
    /**
     * 领导的工号
     */

    @Size(max = 30, message = "领导的工号长度不能超过30") 
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
    @NotEmpty(message = "电话不能为空") 
    @Size(max = 32, message = "电话长度不能超过32") 
    @ApiModelProperty(value="电话")
    private String mobile;
        
    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空") 
    @Size(max = 256, message = "邮箱长度不能超过256") 
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
    @NotEmpty(message = "公司名称不能为空") 
    @Size(max = 120, message = "公司名称长度不能超过120") 
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

    @Size(max = 120, message = "部门名称长度不能超过120") 
    @ApiModelProperty(value="部门名称")
    private String deptName;
        
    /**
     * 状态（1：在职，0：离职）
     */
    @NotEmpty(message = "状态（1：在职，0：离职）不能为空")
    @ApiModelProperty(value="状态（1：在职，0：离职）")
    private Integer status;
        
    /**
     * 性别1：男；2：女
     */
    @NotEmpty(message = "性别1：男；2：女不能为空")
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

    @Size(max = 400, message = "地址长度不能超过400") 
    @ApiModelProperty(value="地址")
    private String address;
        
    /**
     * 传真
     */

    @Size(max = 20, message = "传真长度不能超过20") 
    @ApiModelProperty(value="传真")
    private String fax;
        
    /**
     * Userid
     */

    @ApiModelProperty(value="userId")
    private Long userId;


    @TableField(exist = false)
    private String positionName;
    @TableField(exist = false)
    private String jobGradeName;
    @TableField(exist = false)
    private String deptCode;
    @TableField(exist = false)
    private String companyCode;
    //人员拥有的角色列表
    @TableField(exist = false)
    private List<Role> roles;
    @TableField(exist = false)
    private List<Long> companyIds;
    @TableField(exist = false)
    private List<Long> deptIds;


    @Builder
    public Staff(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser, String code, String name, String positionCode, String jobGradeCode, String leaderCode, String avatar, String mobile, String email, Long companyId, String companyName, Long deptId, String deptName, Integer status, Integer sex, LocalDateTime leaveDate, String address, String fax, Long userId) {
        super(id, createTime, createUser, updateTime, updateUser);
        this.code = code;
        this.name = name;
        this.positionCode = positionCode;
        this.jobGradeCode = jobGradeCode;
        this.leaderCode = leaderCode;
        this.avatar = avatar;
        this.mobile = mobile;
        this.email = email;
        this.companyId = companyId;
        this.companyName = companyName;
        this.deptId = deptId;
        this.deptName = deptName;
        this.status = status;
        this.sex = sex;
        this.leaveDate = leaveDate;
        this.address = address;
        this.fax = fax;
        this.userId = userId;
    }

    public String toString()
    {
        //MULTI_LINE_STYLE 每一行一个字段
        //SHORT_PREFIX_STYLE 不换行，用逗号分隔
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(
                this, ToStringStyle.SHORT_PREFIX_STYLE) {
            @Override
            protected boolean accept(Field field) {
                return !field.getName().equals("avatar");
            }
        };

        return builder.toString();
    }


}
