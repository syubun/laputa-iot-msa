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

import com.laputa.iot.common.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 客户端信息
 * </p>
 *
 * @author sommer.jiang
 * @since 2022-08-14
 */
@Data
@ApiModel(value = "客户端信息")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
public class SysOauthClientDetails extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;


	/**
	 * 客户端ID
	 */
	@NotBlank(message = "client_id 不能为空")
	@ApiModelProperty(value = "客户端id")
	private String clientId;

	/**
	 * 客户端密钥
	 */
	@NotBlank(message = "client_secret 不能为空")
	@ApiModelProperty(value = "客户端密钥")
	private String clientSecret;

	/**
	 * 资源ID
	 */
	@ApiModelProperty(value = "资源id列表")
	private String resourceIds;

	/**
	 * 作用域
	 */
	@NotBlank(message = "scope 不能为空")
	@ApiModelProperty(value = "作用域")
	private String scope;

	/**
	 * 授权方式[A,B,C]
	 */
	@ApiModelProperty(value = "授权方式")
	private String[] authorizedGrantTypes;

	/**
	 * 回调地址
	 */
	@ApiModelProperty(value = "回调地址")
	private String webServerRedirectUri;

	/**
	 * 权限
	 */
	@ApiModelProperty(value = "权限列表")
	private String authorities;

	/**
	 * 请求令牌有效时间
	 */
	@ApiModelProperty(value = "请求令牌有效时间")
	private Integer accessTokenValidity;

	/**
	 * 刷新令牌有效时间
	 */
	@ApiModelProperty(value = "刷新令牌有效时间")
	private Integer refreshTokenValidity;

	/**
	 * 扩展信息
	 */
	@ApiModelProperty(value = "扩展信息")
	private String additionalInformation;

	/**
	 * 是否自动放行
	 */
	@ApiModelProperty(value = "是否自动放行")
	private String autoapprove;


}
