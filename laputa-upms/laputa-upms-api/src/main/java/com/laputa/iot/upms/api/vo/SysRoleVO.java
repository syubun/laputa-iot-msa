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

package com.laputa.iot.upms.api.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 系统角色前端
 *
 * @author sommer.jiang
 * @date 2021-07-24 13:36:35
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@ApiModel(value = "系统角色前端展示")
public class SysRoleVO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	/**
	 * 角色名称
	 */

	@ApiModelProperty(value="角色名称")
	private String name;

	/**
	 * 角色标识
	 */

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
	@ApiModelProperty(value="数据权限类型")
	private Integer dsType;

	/**
	 * 数据权限作用范围
	 */
	@ApiModelProperty(value="数据权限作用范围")
	private String dsScope;


	@ApiModelProperty(value = "主键")

	protected Long id;

	@ApiModelProperty(value = "创建时间")
	protected LocalDateTime createTime;



	@ApiModelProperty(value = "租户隔离")
	protected Long tenantId;

}
