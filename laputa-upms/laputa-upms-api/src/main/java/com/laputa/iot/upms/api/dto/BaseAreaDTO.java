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
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value = "区域信息")
public class BaseAreaDTO  implements  Serializable{

    private static final long serialVersionUID = 1L;

    

    /**
     * 编码
     */
    @ApiModelProperty(value="编码")
    private String code;

    

    /**
     * 父编码
     */
    @ApiModelProperty(value="父编码")
    private String pcode;

    

    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;

    

    /**
     * deleted
     */
    @ApiModelProperty(value="deleted")
    private Integer deleted;

    

    /**
     * createTime
     */
    @ApiModelProperty(value="createTime")
    private LocalDateTime createTime;

    

    /**
     * createUser
     */
    @ApiModelProperty(value="createUser")
    private String createUser;

    

    /**
     * updateTime
     */
    @ApiModelProperty(value="updateTime")
    private LocalDateTime updateTime;

    

    /**
     * updateUser
     */
    @ApiModelProperty(value="updateUser")
    private String updateUser;

    

    /**
     * version
     */
    @ApiModelProperty(value="version")
    private Integer version;

    


}
