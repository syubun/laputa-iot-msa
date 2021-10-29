/*
 *
 *      Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the www.laputa-iot.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: SommerJiang (sommer_jiang@163.com)
 *
 */

package com.laputa.iot.upms.api.entity;


import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 系统角色
 *
 * @author sommer.jiang
 * @date 2021-07-24 13:36:35
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统角色")
public class SysRole extends BaseEntity<Long> {


	private static final long serialVersionUID = -1518363066823459907L;
	/**
	 * 角色名称
	 */
	@NotBlank(message = "角色名称不能为空")
	@ApiModelProperty(value="角色名称")
	private String name;

	/**
	 * 角色标识
	 */
	@NotBlank(message = "角色标识不能为空")
	@ApiModelProperty(value="角色标识")
	private String code;

	/**
	 * 角色描述
	 */
	@ApiModelProperty(value="角色描述")
	private String roleDesc;

	/**
	 * 数据权限类型
	 */
	@NotNull(message = "数据权限类型不能为空")
	@ApiModelProperty(value="数据权限类型 0：All 1：数据权限")
	private Integer dsType;

	/**
	 * 数据权限作用范围
	 */
	@ApiModelProperty(value="数据权限作用范围，以1,2,3标识")
	private String dsScope;

	@Builder
	public SysRole(String name, String code, String roleDesc, Integer dsType, String dsScope) {
		this.name = name;
		this.code = code;
		this.roleDesc = roleDesc;
		this.dsType = dsType;
		this.dsScope = dsScope;
		this.tenantId = 1l;
	}
}
