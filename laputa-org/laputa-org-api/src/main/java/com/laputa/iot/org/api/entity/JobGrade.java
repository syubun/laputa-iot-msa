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
 * 工作等级表
 *
 * @author Sommer
 * @date 2021-10-03 11:35:01
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("org_job_grade")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "工作等级表")
public class JobGrade extends BaseEntity<Long> {


        
    /**
     * 编码
     */
    
    @Size(max = 80, message = "编码长度不能超过80") 
    @ApiModelProperty(value="编码")
    private String code;
        
    /**
     * 类型ID
     */
    
    
    @ApiModelProperty(value="类型ID")
    private Long typeId;
        
    /**
     * 类别编码
     */
    @NotEmpty(message = "类别编码不能为空") 
    @Size(max = 80, message = "类别编码长度不能超过80") 
    @ApiModelProperty(value="类别编码")
    private String typeCode;
        
    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空") 
    @Size(max = 80, message = "名称长度不能超过80") 
    @ApiModelProperty(value="名称")
    private String name;
        
    /**
     * 排序号
     */
    @NotEmpty(message = "排序号不能为空") 
    
    @ApiModelProperty(value="排序号")
    private Integer orderNo;

    @Builder
    public JobGrade(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser, String code, Long typeId, String typeCode, String name, Integer orderNo) {
        super(id, createTime, createUser, updateTime, updateUser);
        this.code = code;
        this.typeId = typeId;
        this.typeCode = typeCode;
        this.name = name;
        this.orderNo = orderNo;
    }
}
