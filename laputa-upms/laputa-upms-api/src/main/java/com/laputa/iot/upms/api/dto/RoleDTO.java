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

package com.laputa.iot.upms.api.dto;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @author sommer.jiang
 * @date 2017/11/5
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "系统权限传输对象")
public class RoleDTO implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	protected Long id;

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
	@ApiModelProperty(value="数据权限类型")
	private Integer dsType;

	/**
	 * 数据权限作用范围
	 */
	@ApiModelProperty(value="数据权限作用范围")
	private String dsScope;

	/**
	 * 权限菜单
	 */
	@ApiModelProperty(value="授权菜单")
	private String menuIds;

	@ApiModelProperty(value="租户")
	private Long tenantId;

	Integer deleted ;

	@Builder
	public RoleDTO(String name, String code, String roleDesc, Integer dsType, String dsScope) {
		this.name = name;
		this.code = code;
		this.roleDesc = roleDesc;
		this.dsType = dsType;
		this.dsScope = dsScope;
		this.tenantId = 1l;
	}

	@Override
	public Object clone() {
		//支持克隆  提高性能  仅仅是浅克隆
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return new Exception("克隆失败");
		}
	}


}
