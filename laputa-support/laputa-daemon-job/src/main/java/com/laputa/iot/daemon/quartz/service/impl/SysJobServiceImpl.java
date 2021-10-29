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

package com.laputa.iot.daemon.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.daemon.quartz.entity.SysJob;
import com.laputa.iot.daemon.quartz.mapper.SysJobMapper;
import com.laputa.iot.daemon.quartz.service.SysJobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 定时任务调度表
 *
 * @author frwcloud
 * @date 2019-01-27 10:04:42
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

}
