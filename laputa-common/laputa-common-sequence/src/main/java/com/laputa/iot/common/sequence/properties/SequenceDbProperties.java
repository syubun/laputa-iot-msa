package com.laputa.iot.common.sequence.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sommer.jiang
 * @date 2021/5/26
 * <p>
 * 发号器DB配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "laputa.xsequence.db")
public class SequenceDbProperties extends BaseSequenceProperties {

	/**
	 * 表名称
	 */
	private String tableName = "laputa_sequence";

	/**
	 * 重试次数
	 */
	private int retryTimes = 1;

}