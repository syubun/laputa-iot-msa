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

package com.laputa.iot.upms.api.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限键值表
 *
 * @author Sommer
 * @date 2021-09-08 12:57:42
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@ApiModel(value = "权限键值表")
public class PrivilegeValueDTO  implements  Serializable{

    private static final long serialVersionUID = 1L;

    

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;

    

    /**
     * 整型的位
     */
    @ApiModelProperty(value="整型的位")
    private Integer position;

    

    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;

    

    /**
     * 排序号
     */
    @ApiModelProperty(value="排序号")
    private Integer orderNo;

    

    /**
     * 权限图标
     */
    @ApiModelProperty(value="权限图标")
    private String icon;

    

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    

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
