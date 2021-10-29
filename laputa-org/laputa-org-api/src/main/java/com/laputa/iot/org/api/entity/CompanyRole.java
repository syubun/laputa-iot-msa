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

import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;


/**
 * 公司角色关联表
 *
 * @author Sommer
 * @date 2021-09-30 14:48:44
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("org_company_role")
@ApiModel(value = "公司角色关联表")
public class CompanyRole extends Model<CompanyRole> implements  Serializable{



        
    /**
     * 公司ID
     */
    
    
    @ApiModelProperty(value="公司ID")
    private Long companyId;
        
    /**
     * 角色id
     */
    
    
    @ApiModelProperty(value="角色id")
    private Long roleId;
        
    /**
     * 截止日期
     */
    @NotEmpty(message = "截止日期不能为空")
    @ApiModelProperty(value="截止日期")
    private LocalDateTime endDate;
        
    /**
     * 有效期
     */
    @NotEmpty(message = "有效期不能为空")
    @ApiModelProperty(value="有效期")
    private Integer validMonth;
                                


}
