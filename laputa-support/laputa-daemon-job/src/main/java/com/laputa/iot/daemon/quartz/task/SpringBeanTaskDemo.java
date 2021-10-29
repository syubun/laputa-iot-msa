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

package com.laputa.iot.daemon.quartz.task;

import com.laputa.iot.daemon.quartz.constants.LaputaQuartzEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Sommer.Jiang
 */
@Slf4j
@Component("demo")
public class SpringBeanTaskDemo {

	/**
	 * 测试Spring Bean的演示方法
	 */
	@SneakyThrows
	public String demoMethod(String para) {
		log.info("测试于:{}，输入参数{}", LocalDateTime.now(), para);
		return LaputaQuartzEnum.JOB_LOG_STATUS_SUCCESS.getType();
	}

}
