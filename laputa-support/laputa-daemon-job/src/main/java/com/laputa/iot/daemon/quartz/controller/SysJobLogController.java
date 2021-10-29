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

package com.laputa.iot.daemon.quartz.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.daemon.quartz.entity.SysJobLog;
import com.laputa.iot.daemon.quartz.service.SysJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author frwcloud
 * <p>
 * 定时任务执行日志表
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-job-log")
@Api(value = "sys-job-log", tags = "定时任务日志")
public class SysJobLogController {

	private final SysJobLogService sysJobLogService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysJobLog 定时任务执行日志表
	 * @return
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页定时任务日志查询")
	public R getSysJobLogPage(Page page, SysJobLog sysJobLog) {
		return R.ok(sysJobLogService.page(page, Wrappers.query(sysJobLog)));
	}

}
