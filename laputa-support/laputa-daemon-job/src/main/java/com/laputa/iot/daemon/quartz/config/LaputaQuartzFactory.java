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

package com.laputa.iot.daemon.quartz.config;

import com.laputa.iot.daemon.quartz.constants.LaputaQuartzEnum;
import com.laputa.iot.daemon.quartz.entity.SysJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sommer.Jiang
 *
 * <p>
 * 动态任务工厂
 */
@Slf4j
@DisallowConcurrentExecution
public class LaputaQuartzFactory implements Job {

	@Autowired
	private LaputaQuartzInvokeFactory laputaQuartzInvokeFactory;

	@Override
	@SneakyThrows
	public void execute(JobExecutionContext jobExecutionContext) {
		SysJob sysJob = (SysJob) jobExecutionContext.getMergedJobDataMap()
				.get(LaputaQuartzEnum.SCHEDULE_JOB_KEY.getType());
		laputaQuartzInvokeFactory.init(sysJob, jobExecutionContext.getTrigger());
	}

}
