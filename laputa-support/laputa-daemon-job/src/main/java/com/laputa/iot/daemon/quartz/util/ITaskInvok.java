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

import com.laputa.iot.daemon.quartz.entity.SysJob;
import com.laputa.iot.daemon.quartz.exception.TaskException;

/**
 * 定时任务反射实现接口类
 *
 * @author sommer.jiang
 */
public interface ITaskInvok {

	/**
	 * 执行反射方法
	 * @param sysJob 配置类
	 * @throws TaskException
	 */
	void invokMethod(SysJob sysJob) throws TaskException;

}
