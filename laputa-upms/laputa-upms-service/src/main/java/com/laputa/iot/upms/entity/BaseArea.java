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


import com.baomidou.mybatisplus.extension.activerecord.Model;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 区域信息
 *
 * @author Sommer
 * @date 2021-09-22 09:14:19
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("sys_base_area")
@ApiModel(value = "区域信息")
public class BaseArea extends Model<BaseArea> implements  Serializable{


    private static final long serialVersionUID = -4752399227375644068L;
    /**
     * 编码
     */
    
    @Size(max = 10, message = "编码长度不能超过10") 
    @ApiModelProperty(value="编码")
    private String code;
        
    /**
     * 父编码
     */
    @NotEmpty(message = "父编码不能为空") 
    @Size(max = 10, message = "父编码长度不能超过10") 
    @ApiModelProperty(value="父编码")
    private String pcode;
        
    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空") 
    @Size(max = 64, message = "名称长度不能超过64") 
    @ApiModelProperty(value="名称")
    private String name;
                            


}
