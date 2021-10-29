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
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author sommer.jiang
 * @since 2017-11-20
 */
@Data
@ApiModel(value = "日志")
public class SysLog  extends BaseEntity<Long> {




	/**
	 * 日志类型
	 */
	@NotBlank(message = "日志类型不能为空")
	@ApiModelProperty(value = "日志类型")
	private String type;

	/**
	 * 日志标题
	 */
	@NotBlank(message = "日志标题不能为空")
	@ApiModelProperty(value = "日志标题")
	private String title;


	/**
	 * 操作者
	 */

	@ApiModelProperty(value = "操作者")
	private String createBy;
	/**
	 * 操作IP地址
	 */
	@ApiModelProperty(value = "操作ip地址")
	private String remoteAddr;

	/**
	 * 用户代理
	 */
	@ApiModelProperty(value = "用户代理")
	private String userAgent;

	/**
	 * 请求URI
	 */
	@ApiModelProperty(value = "请求uri")
	private String requestUri;

	/**
	 * 操作方式
	 */
	@ApiModelProperty(value = "操作方式")
	private String method;

	/**
	 * 操作提交的数据
	 */
	@ApiModelProperty(value = "提交数据")
	private String params;

	/**
	 * 执行时间
	 */
	@ApiModelProperty(value = "方法执行时间")
	private Long time;

	/**
	 * 异常信息
	 */
	@ApiModelProperty(value = "异常信息")
	private String exception;

	/**
	 * 服务ID
	 */
	@ApiModelProperty(value = "应用标识")
	private String serviceId;


}
