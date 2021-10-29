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

package com.laputa.iot.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.log.api.dto.SysLogDTO;
import com.laputa.iot.log.api.vo.LogVO;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.security.annotation.Inner;
import com.laputa.iot.log.service.ILogService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author sommer.jiang
 * @since 2017-11-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
@Api(value = "log", tags = "日志管理模块")
public class SysLogController {

	private final ILogService sysLogService;

	/**
	 * 简单分页查询
	 *
	 * @param page 分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public R getLogPage(Page page, SysLogDTO sysLog) {
		return R.ok(sysLogService.getLogByPage(page, sysLog));
	}

	/**
	 * 删除日志
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_log_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(sysLogService.removeById(id));
	}

	/**
	 * 插入日志
	 * @param sysLog 日志实体
	 * @return success/false
	 */
	@Inner
	@PostMapping("/save")
	public R save(@Valid @RequestBody SysLogDTO sysLog) {
		return R.ok(sysLogService.saveLog(sysLog));
	}

	/**
	 * 批量插入前端异常日志
	 * @param preLogVoList 日志实体
	 * @return success/false
	 */
	@PostMapping("/logs")
	public R saveBatchLogs(@RequestBody List<LogVO> preLogVoList) {
		return R.ok(sysLogService.saveBatchLogs(preLogVoList));
	}

}
