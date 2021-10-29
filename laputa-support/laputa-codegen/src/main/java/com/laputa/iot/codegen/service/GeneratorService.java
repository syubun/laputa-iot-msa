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

package com.laputa.iot.codegen.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.codegen.entity.GenConfig;

/**
 * @author sommer.jiang
 * @date 2018/7/29
 */
public interface GeneratorService {

	/**
	 * 生成代码
	 * @param genConfig 生成信息
	 * @return
	 */
	byte[] generatorCode(GenConfig genConfig);

	/**
	 * 分页查询表
	 * @param page 分页信息
	 * @param tableName 表名
	 * @param name 数据源ID
	 * @return
	 */
	IPage<Map<String, Object>> getPage(Page page, String tableName, String name);

	/**
	 * 预览代码
	 * @param genConfig 生成信息
	 * @return
	 */
	Map<String, String> previewCode(GenConfig genConfig);

}
