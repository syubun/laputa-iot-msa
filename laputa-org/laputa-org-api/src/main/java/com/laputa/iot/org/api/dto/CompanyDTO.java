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

package com.laputa.iot.org.api.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公司表
 *
 * @author Sommer
 * @date 2021-09-30 14:10:33
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@ApiModel(value = "公司表")
public class CompanyDTO  implements  Serializable{

    private static final long serialVersionUID = 1L;

    

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;

    

    /**
     * 上级公司id
     */
    @ApiModelProperty(value="上级公司id")
    private Long pid;

    

    /**
     * 公司名称
     */
    @ApiModelProperty(value="公司名称")
    private String name;

    

    /**
     * 公司简称
     */
    @ApiModelProperty(value="公司简称")
    private String shortName;

    

    /**
     * 公司名称多语言
     */
    @ApiModelProperty(value="公司名称多语言")
    private String tName;

    

    /**
     * 公司code
     */
    @ApiModelProperty(value="公司code")
    private String code;

    

    /**
     * orderNo
     */
    @ApiModelProperty(value="orderNo")
    private Integer orderNo;

    

    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String descr;

    

    /**
     * 状态 1启用 0禁用
     */
    @ApiModelProperty(value="状态 1启用 0禁用")
    private Integer status;

    

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
