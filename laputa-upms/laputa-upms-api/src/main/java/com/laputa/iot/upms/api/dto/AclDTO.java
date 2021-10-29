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


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.laputa.iot.common.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


/**
 * 权限提交
 *
 * @author sommer.jiang
 * @date 2021-07-26 10:37:00
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@ApiModel(value = "权限参数")
public class AclDTO implements Serializable {


    /**
     * 权限ID
     */
    private Long id;


    /**
     * 临时变量 操作权限集合
     */
    private List<PrivilegeValueDTO> pvs;


    private String  menuSn;

    private Long   menuId;

    private String  roleSn;

    private Long   roleId;

}
