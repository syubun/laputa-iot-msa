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

package com.laputa.iot.common.sequence;

import com.laputa.iot.common.sequence.properties.SequenceSnowflakeProperties;
import com.laputa.iot.common.sequence.builder.SnowflakeSeqBuilder;
import com.laputa.iot.common.sequence.sequence.Sequence;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sommer.jiang
 * @date 2019-05-26
 */
@Configuration
@ComponentScan("com.laputa.iot.common.sequence")
@ConditionalOnMissingBean(Sequence.class)
public class SequenceAutoConfiguration {

	/**
	 * snowflak 算法作为发号器实现
	 * @param properties
	 * @return
	 */
	@Bean
	@ConditionalOnBean(SequenceSnowflakeProperties.class)
	public Sequence snowflakeSequence(SequenceSnowflakeProperties properties) {
		return SnowflakeSeqBuilder.create().datacenterId(properties.getDatacenterId())
				.workerId(properties.getWorkerId()).build();
	}

}