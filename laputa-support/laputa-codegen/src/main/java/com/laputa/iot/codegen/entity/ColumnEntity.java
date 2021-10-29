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

package com.laputa.iot.codegen.entity;

import lombok.Data;

/**
 * @author sommer.jiang
 * @date 2018/07/29 列属性： https://blog.csdn.net/lkforce/article/details/79557482
 */
@Data
public class ColumnEntity {

	/**
	 * 列表
	 */
	private String columnName;

	/**
	 * 数据类型
	 */
	private String dataType;

	/**
	 * JAVA 数据类型
	 */
	private String javaType;

	/**
	 * 备注
	 */
	private String comments;

	/**
	 * 驼峰属性
	 */
	private String caseAttrName;

	/**
	 * 普通属性
	 */
	private String lowerAttrName;

	/**
	 * 属性类型
	 */
	private String attrType;

	/**
	 * 其他信息
	 */
	private String extra;

	/**
	 * 字段类型
	 */
	private String columnType;
	/**
	 * 限制字段大小
	 */
	private String size;

	/**
	 * 是否可以为空
	 */
	private Boolean nullable;

	/**
	 * 是否隐藏
	 */
	private Boolean hidden;

	/**
	 * 是否基础字段
	 */
	private Boolean isBase;

	/**
	 * tsType类型
	 */
	private String tsType;

}
