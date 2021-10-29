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

package com.laputa.iot.codegen.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.codegen.entity.GenConfig;
import com.laputa.iot.codegen.service.GenTableColumnService;
import com.laputa.iot.codegen.service.GeneratorService;
import com.laputa.iot.common.core.base.dto.R;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 代码生成器
 *
 * @author sommer.jiang
 * @date 2018-07-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class GeneratorController {

	private final GeneratorService generatorService;

	private final GenTableColumnService columnService;

	/**
	 * 列表
	 * @param tableName 参数集
	 * @param dsName 数据源编号
	 * @return 数据库表
	 */
	@GetMapping("/page")
	public R getPage(Page page, String tableName, String dsName) {
		return R.ok(generatorService.getPage(page, tableName, dsName));
	}

	/**
	 * 预览代码
	 * @param genConfig 数据表配置
	 * @return
	 */
	@GetMapping("/preview")
	public R previewCode(GenConfig genConfig) {
		return R.ok(generatorService.previewCode(genConfig));
	}

	/**
	 * 查询表的列信息
	 * @param page 分页
	 * @param genConfig 数据表配置
	 * @return
	 */
	@GetMapping("/table")
	public R getTable(Page page, GenConfig genConfig) {
		return R.ok(columnService.listTable(page, genConfig));
	}

	/**
	 * 生成代码
	 * @param genConfig 数据表配置
	 */
	@SneakyThrows
	@PostMapping("/code")
	public void generatorCode(@RequestBody GenConfig genConfig, HttpServletResponse response) {
		byte[] data = generatorService.generatorCode(genConfig);
		response.reset();
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
				String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}

}
