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

package com.laputa.iot.daemon.quartz.util;

import cn.hutool.core.util.StrUtil;
import com.laputa.iot.common.data.tenant.TenantBroker;
import com.laputa.iot.daemon.quartz.constants.LaputaQuartzEnum;
import com.laputa.iot.daemon.quartz.entity.SysJob;
import com.laputa.iot.daemon.quartz.entity.SysJobLog;
import com.laputa.iot.daemon.quartz.event.SysJobLogEvent;
import com.laputa.iot.daemon.quartz.service.SysJobService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.quartz.CronTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.time.ZoneId;
import java.util.Date;

/**
 * 定时任务反射工具类
 *
 * @author Sommer.Jiang
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class TaskInvokUtil {

	private final ApplicationEventPublisher publisher;

	@Autowired
	private SysJobService sysJobService;

	@SneakyThrows
	public void invokMethod(SysJob sysJob, Trigger trigger) {

		// 执行开始时间
		long startTime;
		// 执行结束时间
		long endTime;
		// 获取执行开始时间
		startTime = System.currentTimeMillis();
		// 更新定时任务表内的状态、执行时间、上次执行时间、下次执行时间等信息
		SysJob updateSysjob = new SysJob();
		updateSysjob.setJobId(sysJob.getJobId());
		// 日志
		SysJobLog sysJobLog = new SysJobLog();
		sysJobLog.setJobId(sysJob.getJobId());
		sysJobLog.setJobName(sysJob.getJobName());
		sysJobLog.setJobGroup(sysJob.getJobGroup());
		sysJobLog.setJobOrder(sysJob.getJobOrder());
		sysJobLog.setJobType(sysJob.getJobType());
		sysJobLog.setExecutePath(sysJob.getExecutePath());
		sysJobLog.setClassName(sysJob.getClassName());
		sysJobLog.setMethodName(sysJob.getMethodName());
		sysJobLog.setMethodParamsValue(sysJob.getMethodParamsValue());
		sysJobLog.setCronExpression(sysJob.getCronExpression());
		sysJobLog.setTenantId(sysJob.getTenantId());
		try {
			// 执行任务
			ITaskInvok iTaskInvok = TaskInvokFactory.getInvoker(sysJob.getJobType());
			// 确保租户上下文有值，使得当前线程中的多租户特性生效。
			TenantBroker.runAs(sysJob.getTenantId(), tenantId -> iTaskInvok.invokMethod(sysJob));

			// 记录成功状态
			sysJobLog.setJobMessage(LaputaQuartzEnum.JOB_LOG_STATUS_SUCCESS.getDescription());
			sysJobLog.setJobLogStatus(LaputaQuartzEnum.JOB_LOG_STATUS_SUCCESS.getType());
			// 任务表信息更新
			updateSysjob.setJobExecuteStatus(LaputaQuartzEnum.JOB_LOG_STATUS_SUCCESS.getType());
		}
		catch (Throwable e) {
			log.error("定时任务执行失败，任务名称：{}；任务组名：{}，cron执行表达式：{}，执行时间：{}", sysJob.getJobName(), sysJob.getJobGroup(),
					sysJob.getCronExpression(), new Date());
			// 记录失败状态
			sysJobLog.setJobMessage(LaputaQuartzEnum.JOB_LOG_STATUS_FAIL.getDescription());
			sysJobLog.setJobLogStatus(LaputaQuartzEnum.JOB_LOG_STATUS_FAIL.getType());
			sysJobLog.setExceptionInfo(StrUtil.sub(e.getMessage(), 0, 2000));
			// 任务表信息更新
			updateSysjob.setJobExecuteStatus(LaputaQuartzEnum.JOB_LOG_STATUS_FAIL.getType());
		}
		finally {
			// 记录执行时间 立刻执行使用的是simpleTeigger
			if (trigger instanceof CronTrigger) {
				updateSysjob.setStartTime(
						trigger.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				updateSysjob.setPreviousTime(
						trigger.getPreviousFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				updateSysjob.setNextTime(
						trigger.getNextFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			}
			// 记录执行时长
			endTime = System.currentTimeMillis();
			sysJobLog.setExecuteTime(String.valueOf(endTime - startTime));

			publisher.publishEvent(new SysJobLogEvent(sysJobLog));
			sysJobService.updateById(updateSysjob);
		}
	}

}
