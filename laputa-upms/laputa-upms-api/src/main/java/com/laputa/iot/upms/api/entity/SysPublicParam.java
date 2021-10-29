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
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公共参数配置
 *
 * @author sommer
 * @date 2019-04-29
 */
@Data
@ApiModel(value = "公共参数")
@EqualsAndHashCode(callSuper = true)
public class SysPublicParam extends Model<SysPublicParam> {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	@ApiModelProperty(value = "公共参数编号")
	private Long publicId;

	/**
	 * 公共参数名称
	 */
	@ApiModelProperty(value = "公共参数名称", required = true, example = "公共参数名称")
	private String publicName;

	/**
	 * 公共参数地址值,英文大写+下划线
	 */
	@ApiModelProperty(value = "键[英文大写+下划线]", required = true, example = "LAPUTA_PUBLIC_KEY")
	private String publicKey;

	/**
	 * 值
	 */
	@ApiModelProperty(value = "值", required = true, example = "999")
	private String publicValue;

	/**
	 * 状态（1有效；2无效；）
	 */
	@ApiModelProperty(value = "标识[1有效；2无效]", example = "1")
	private String status;



	/**
	 * 公共参数编码
	 */
	@ApiModelProperty(value = "编码", example = "^(LAPUTA|LAPUTAIOT)$")
	private String validateCode;



	/**
	 * 是否是系统内置
	 */
	@TableField(value = "`system`")
	@ApiModelProperty(value = "是否是系统内置")
	private String system;

	/**
	 * 配置类型：0-默认；1-检索；2-原文；3-报表；4-安全；5-文档；6-消息；9-其他
	 */
	@ApiModelProperty(value = "类型[1-检索；2-原文...]", example = "1")
	private String publicType;

}
