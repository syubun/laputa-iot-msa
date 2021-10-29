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

package com.laputa.iot.upms.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author sommer.jiang
 * @since 2017-10-29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "用户角色")
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId;


	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;



	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色id")
	private Long roleId;



	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名")
	private String rolename;

}
