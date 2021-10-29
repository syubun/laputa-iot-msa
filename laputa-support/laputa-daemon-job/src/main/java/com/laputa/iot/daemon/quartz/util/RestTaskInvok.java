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

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.daemon.quartz.entity.SysJob;
import com.laputa.iot.daemon.quartz.exception.TaskException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务rest反射实现
 *
 * @author Sommer.Jiang
 */
@Slf4j
@AllArgsConstructor
@Component("restTaskInvok")
public class RestTaskInvok implements ITaskInvok {

	@Override
	public void invokMethod(SysJob sysJob) throws TaskException {
		try {
			HttpRequest request = HttpUtil.createGet(sysJob.getExecutePath());
			if (sysJob.getTenantId() != null) {
				request.header(CommonConstants.TENANT_ID, sysJob.getTenantId().toString());
			}
			request.execute();
		}
		catch (Exception e) {
			log.error("定时任务restTaskInvok异常,执行任务：{}", sysJob.getExecutePath());
			throw new TaskException("定时任务restTaskInvok业务执行失败,任务：" + sysJob.getExecutePath());
		}
	}

}
