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

package com.laputa.iot.daemon.quartz.event;

import com.laputa.iot.daemon.quartz.config.LaputaQuartzInvokeFactory;
import com.laputa.iot.daemon.quartz.service.SysJobLogService;
import com.laputa.iot.daemon.quartz.util.TaskInvokUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sommer.jiang
 * @date 2018/6/28
 * <p>
 * 多线程自动配置
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class EventAutoConfiguration {

	@Autowired
	private TaskInvokUtil taskInvokUtil;

	@Autowired
	private SysJobLogService sysJobLogService;

	@Bean
	public SysJobListener sysJobListener() {
		return new SysJobListener(taskInvokUtil);
	}

	@Bean
	public LaputaQuartzInvokeFactory laputaQuartzInvokeFactory(ApplicationEventPublisher publisher) {
		return new LaputaQuartzInvokeFactory(publisher);
	}

	@Bean
	public SysJobLogListener sysJobLogListener() {
		return new SysJobLogListener(sysJobLogService);
	}

	@Bean
	public TaskInvokUtil taskInvokUtil(ApplicationEventPublisher publisher) {
		return new TaskInvokUtil(publisher);
	}

}
