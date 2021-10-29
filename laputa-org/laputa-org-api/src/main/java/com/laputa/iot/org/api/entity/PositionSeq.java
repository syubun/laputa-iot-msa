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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 岗位编码序列
 *
 * @author Sommer.Jiang
 * @date 2021-09-29 21:21:09
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("org_position_seq")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "岗位编码序列")
public class PositionSeq extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

        
    /**
     * 编码
     */
    
    @Size(max = 80, message = "编码长度不能超过80") 
    @ApiModelProperty(value="编码")
    private String code;
        
    /**
     * 父级Id
     */
    
    
    @ApiModelProperty(value="父级Id")
    private Long pid;
        
    /**
     * 名称
     */
    @NotEmpty(message = "名称不能为空") 
    @Size(max = 80, message = "名称长度不能超过80") 
    @ApiModelProperty(value="名称")
    private String name;
        
    /**
     * 状态1启用；0停用
     */
    @NotEmpty(message = "状态1启用；0停用不能为空") 
    
    @ApiModelProperty(value="状态1启用；0停用")
    private Integer status;
        
    /**
     * 排序编号
     */
    @NotEmpty(message = "排序编号不能为空") 
    
    @ApiModelProperty(value="排序编号")
    private Integer orderNo;
        
    /**
     * 备注
     */
    @NotEmpty(message = "备注不能为空") 
    @Size(max = 255, message = "备注长度不能超过255") 
    @ApiModelProperty(value="备注")
    private String note;

@Builder
    public PositionSeq(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser, String code, Long pid, String name, Integer status, Integer orderNo, String note) {
        super(id, createTime, createUser, updateTime, updateUser);
        this.code = code;
        this.pid = pid;
        this.name = name;
        this.status = status;
        this.orderNo = orderNo;
        this.note = note;
    }
}
