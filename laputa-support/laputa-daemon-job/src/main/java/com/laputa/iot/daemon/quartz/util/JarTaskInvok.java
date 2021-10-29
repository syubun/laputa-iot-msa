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
import com.laputa.iot.daemon.quartz.entity.SysJob;
import com.laputa.iot.daemon.quartz.exception.TaskException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务可执行jar反射实现
 *
 * @author Sommer.Jiang
 */
@Slf4j
@Component("jarTaskInvok")
public class JarTaskInvok implements ITaskInvok {

	@Override
	public void invokMethod(SysJob sysJob) throws TaskException {
		ProcessBuilder processBuilder = new ProcessBuilder();
		File jar = new File(sysJob.getExecutePath());
		processBuilder.directory(jar.getParentFile());
		List<String> commands = new ArrayList<>();
		commands.add("java");
		commands.add("-jar");
		commands.add(sysJob.getExecutePath());
		if (StrUtil.isNotEmpty(sysJob.getMethodParamsValue())) {
			commands.add(sysJob.getMethodParamsValue());
		}
		processBuilder.command(commands);
		try {
			processBuilder.start();
		}
		catch (IOException e) {
			log.error("定时任务jar反射执行异常,执行任务：{}", sysJob.getExecutePath());
			throw new TaskException("定时任务jar反射执行异常,执行任务：" + sysJob.getExecutePath());
		}
	}

}
