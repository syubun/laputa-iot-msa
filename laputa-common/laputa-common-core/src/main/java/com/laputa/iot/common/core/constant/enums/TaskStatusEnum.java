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

package com.laputa.iot.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sommer.jiang
 * @date 2018/9/30 流程状态
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

	/**
	 * 未提交
	 */
	UNSUBMIT("0", "未提交"),

	/**
	 * 审核中
	 */
	CHECK("1", "审核中"),

	/**
	 * 已完成
	 */
	COMPLETED("2", "已完成"),

	/**
	 * 驳回
	 */
	OVERRULE("9", "驳回");

	/**
	 * 类型
	 */
	private final String status;

	/**
	 * 描述
	 */
	private final String description;

}
