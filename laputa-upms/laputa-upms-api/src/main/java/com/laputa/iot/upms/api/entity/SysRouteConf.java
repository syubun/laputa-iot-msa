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

package com.laputa.iot.upms.api.entity;


import com.baomidou.mybatisplus.annotation.TableField;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 路由
 *
 * @author sommer.jiang
 * @date 2018-11-06 10:17:18
 */
@Data
@ApiModel(value = "网关路由信息")
public class SysRouteConf extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;



	/**
	 * 路由ID
	 */
	@ApiModelProperty(value = "路由id")
	private String routeId;

	/**
	 * 路由名称
	 */
	@ApiModelProperty(value = "路由名称")
	private String routeName;

	/**
	 * 断言
	 */
	@ApiModelProperty(value = "断言")
	private String predicates;

	/**
	 * 过滤器
	 */
	@ApiModelProperty(value = "过滤器")
	private String filters;

	/**
	 * uri
	 */
	@ApiModelProperty(value = "请求uri")
	private String uri;

	/**
	 * 排序
	 */
	@TableField(value = "`order`")
	@ApiModelProperty(value = "排序值")
	private Integer order;


}
