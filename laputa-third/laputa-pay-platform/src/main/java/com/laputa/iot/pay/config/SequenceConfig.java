package com.laputa.iot.pay.config;

import cn.hutool.core.date.DateUtil;
import com.laputa.iot.common.data.tenant.TenantContextHolder;
import com.laputa.iot.common.sequence.builder.DbSeqBuilder;
import com.laputa.iot.common.sequence.properties.SequenceDbProperties;
import com.laputa.iot.common.sequence.sequence.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author sommer.jiang
 * @date 2019-05-26
 * <p>
 * 设置发号器生成规则
 */
@Configuration
public class SequenceConfig {

	/**
	 * 订单流水号发号器
	 * @param dataSource
	 * @param properties
	 * @return
	 */
	@Bean
	public Sequence paySequence(DataSource dataSource, SequenceDbProperties properties) {
		return DbSeqBuilder.create()
				.bizName(() -> String.format("pay_%s_%s", TenantContextHolder.getTenantId(), DateUtil.today()))
				.dataSource(dataSource).step(properties.getStep()).retryTimes(properties.getRetryTimes())
				.tableName(properties.getTableName()).build();
	}

	/**
	 * 通道编号发号器
	 * @param dataSource
	 * @param properties
	 * @return
	 */
	@Bean
	public Sequence channelSequence(DataSource dataSource, SequenceDbProperties properties) {
		return DbSeqBuilder.create().bizName(() -> String.format("channel_%s", TenantContextHolder.getTenantId()))
				.dataSource(dataSource).step(properties.getStep()).retryTimes(properties.getRetryTimes())
				.tableName(properties.getTableName()).build();
	}

}
