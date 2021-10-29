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


import com.baomidou.mybatisplus.annotation.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sommer.jiang
 * @date 2017/11/5
 */
@Data
@ApiModel(value = "系统用户传输对象")
@ToString
public class UserDTO  {

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色id集合")
	private List<Long> roles;


	/**
	 * 新密码
	 */
	@ApiModelProperty(value = "新密码")
	private String newpassword1;

	private static final long serialVersionUID = -6950877265898716315L;
	/**
	 * 用户账号
	 */
	@ApiModelProperty(value="用户账号")
	@NotEmpty(message = "账号不能为空")
	private String username;

	/**
	 * 姓名
	 */
	@ApiModelProperty(value="姓名")
	private String name;

	/**
	 * 密码
	 */
	@ApiModelProperty(value="密码")
	private String password;

	/**
	 * 加密盐
	 */
	@ApiModelProperty(value="加密盐")
	private String salt;

	/**
	 * 手机
	 */
	@ApiModelProperty(value="手机")
	private String phone;

	/**
	 * 邮箱
	 */
	@ApiModelProperty(value="邮箱")
	private String email;

	/**
	 * 头像
	 */
	@ApiModelProperty(value="头像")
	private String avatar;

	/**
	 * 社交登录UID
	 */
	@ApiModelProperty(value="社交登录UID")
	private String socialUid;


	/**
	 * 是否被锁 0启用 1禁用
	 */
	@ApiModelProperty(value="是否被锁 0启用 1禁用")
	private String lockFlag;

	@ApiModelProperty(value = "最后修改时间")
	private LocalDateTime updateTime;

	@ApiModelProperty(value = "最后修改人")
	private Long updateUser;

	@ApiModelProperty(value = "版本号")
	private Integer version;

	@TableId(value = "id", type = IdType.INPUT)
	@ApiModelProperty(value = "主键")

	private Long id;

	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "创建人")
	private Long createUser;



	@ApiModelProperty(value = "租户隔离")
	private Long tenantId;


	private String keyword;
}
