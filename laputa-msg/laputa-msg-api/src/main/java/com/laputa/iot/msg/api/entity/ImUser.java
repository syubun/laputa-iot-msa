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

package com.laputa.iot.msg.api.entity;


import com.laputa.iot.common.core.base.entity.BaseEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 聊天用户
 *
 * @author Sommer
 * @date 2021-10-23 14:18:38
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("msg_im_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "聊天用户")
public class ImUser extends BaseEntity<Long> {


        
    /**
     * 用户账号
     */
    
    @Size(max = 64, message = "用户账号长度不能超过64") 
    @ApiModelProperty(value="用户账号")
    private String username;
        
    /**
     * 姓名
     */
    @NotEmpty(message = "姓名不能为空") 
    @Size(max = 64, message = "姓名长度不能超过64") 
    @ApiModelProperty(value="姓名")
    private String name;
        
    /**
     * 手机
     */
    @NotEmpty(message = "手机不能为空") 
    @Size(max = 20, message = "手机长度不能超过20") 
    @ApiModelProperty(value="手机")
    private String phone;
        
    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空") 
    @Size(max = 255, message = "邮箱长度不能超过255") 
    @ApiModelProperty(value="邮箱")
    private String email;
        
    /**
     * 头像
     */
    @NotEmpty(message = "头像不能为空") 
    
    @ApiModelProperty(value="头像")
    private String avatar;
        
    /**
     * 用户标注
     */
    @NotEmpty(message = "用户标注不能为空") 
    @Size(max = 255, message = "用户标注长度不能超过255") 
    @ApiModelProperty(value="用户标注")
    private String remark;
        
    /**
     * 性别
     */
    @NotEmpty(message = "性别不能为空") 
    
    @ApiModelProperty(value="性别")
    private Integer gender;
        
    /**
     * 状态
     */
    @NotEmpty(message = "状态不能为空") 
    
    @ApiModelProperty(value="状态")
    private Integer status;
                                


}
