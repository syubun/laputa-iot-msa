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

package com.laputa.iot.org.mapper;

import com.laputa.iot.common.data.datascope.LaputaBaseMapper;
import com.laputa.iot.org.api.entity.JobGradeType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职级分类
 *
 * @author Sommer
 * @date 2021-10-03 11:11:22
 */
@Mapper
public interface JobGradeTypeMapper extends LaputaBaseMapper<JobGradeType> {

}
