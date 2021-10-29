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

package com.laputa.iot.codegen.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.codegen.entity.GenConfig;
import com.laputa.iot.codegen.entity.GenFormConf;
import com.laputa.iot.codegen.util.GenUtils;
import com.laputa.iot.codegen.mapper.GenFormConfMapper;
import com.laputa.iot.codegen.mapper.GeneratorMapper;
import com.laputa.iot.codegen.service.GeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sommer.jiang
 * @date 2018-07-30
 * <p>
 * 代码生成器
 */
@Service
@AllArgsConstructor
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {

	private final GenFormConfMapper genFormConfMapper;

	/**
	 * 分页查询表
	 * @param tableName 查询条件
	 * @param dsName
	 * @return
	 */
	@Override
	public IPage<Map<String, Object>> getPage(Page page, String tableName, String dsName) {
		GeneratorMapper mapper = GenUtils.getMapper(dsName);
		// 手动切换数据源
		DynamicDataSourceContextHolder.push(dsName);
		return mapper.queryTable(page, tableName);
	}

	@Override
	public Map<String, String> previewCode(GenConfig genConfig) {
		// 根据tableName 查询最新的表单配置
		List<GenFormConf> formConfList = genFormConfMapper.selectList(Wrappers.<GenFormConf>lambdaQuery()
				.eq(GenFormConf::getTableName, genConfig.getTableName()).orderByDesc(GenFormConf::getCreateTime));

		String tableNames = genConfig.getTableName();
		String dsName = genConfig.getDsName();
		GeneratorMapper mapper = GenUtils.getMapper(genConfig.getDsName());

		for (String tableName : StrUtil.split(tableNames, StrUtil.DASHED)) {
			// 查询表信息
			Map<String, String> table = mapper.queryTable(tableName, dsName);
			// 查询列信息
			List<Map<String, String>> columns = mapper.selectMapTableColumn(tableName, dsName);
			// 生成代码
			if (CollUtil.isNotEmpty(formConfList)) {
				return GenUtils.generatorCode(genConfig, table, columns, null, formConfList.get(0));
			}
			else {
				return GenUtils.generatorCode(genConfig, table, columns, null, null);
			}
		}
		return MapUtil.empty();
	}

	/**
	 * 生成代码
	 * @param genConfig 生成配置
	 * @return
	 */
	@Override
	public byte[] generatorCode(GenConfig genConfig) {
		// 根据tableName 查询最新的表单配置
		List<GenFormConf> formConfList = genFormConfMapper.selectList(Wrappers.<GenFormConf>lambdaQuery()
				.eq(GenFormConf::getTableName, genConfig.getTableName()).orderByDesc(GenFormConf::getCreateTime));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		String tableNames = genConfig.getTableName();
		String dsName = genConfig.getDsName();
		GeneratorMapper mapper = GenUtils.getMapper(dsName);

		for (String tableName : StrUtil.split(tableNames, StrUtil.DASHED)) {
			// 查询表信息
			Map<String, String> table = mapper.queryTable(tableName, dsName);
			// 查询列信息
			List<Map<String, String>> columns = mapper.selectMapTableColumn(tableName, dsName);
			columns.stream().forEach(column ->{
				column.forEach((key,value)->{
					log.info("key:{} value:{}",key,value);

				});
			});
			// 生成代码
			if (CollUtil.isNotEmpty(formConfList)) {
				GenUtils.generatorCode(genConfig, table, columns, zip, formConfList.get(0));
			}
			else {
				GenUtils.generatorCode(genConfig, table, columns, zip, null);
			}
		}
		IoUtil.close(zip);
		return outputStream.toByteArray();
	}

}
