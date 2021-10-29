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
 * 岗位
 *
 * @author Sommer
 * @date 2021-10-03 12:04:34
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@ApiModel(value = "岗位")
public class PositionInfoDTO  implements  Serializable{

    private static final long serialVersionUID = 1L;

    

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;

    

    /**
     * 编码
     */
    @ApiModelProperty(value="编码")
    private String code;

    

    /**
     * 岗位序列ID
     */
    @ApiModelProperty(value="岗位序列ID")
    private Long positionSeqId;

    

    /**
     * 岗位序列编码
     */
    @ApiModelProperty(value="岗位序列编码")
    private String positionSeqCode;

    

    /**
     * 岗位名称
     */
    @ApiModelProperty(value="岗位名称")
    private String name;

    

    /**
     * 排序编号
     */
    @ApiModelProperty(value="排序编号")
    private Integer orderNo;

    

    /**
     * 状态1启用；0停用
     */
    @ApiModelProperty(value="状态1启用；0停用")
    private Integer status;

    

    /**
     * 上级编码
     */
    @ApiModelProperty(value="上级编码")
    private String superiorCode;

    

    /**
     * 成立日期
     */
    @ApiModelProperty(value="成立日期")
    private LocalDateTime startDate;

    

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
