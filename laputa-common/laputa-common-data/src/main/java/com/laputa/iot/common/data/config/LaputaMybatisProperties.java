package com.laputa.iot.common.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

/**
 * Mybatis 配置
 *
 * @author sommer.jiang
 * @date 2021/6/3
 */
@Data
@RefreshScope
@ConfigurationProperties("laputa.mybatis")
public class LaputaMybatisProperties {

	/**
	 * 是否打印可执行 sql
	 */
	private boolean showSql = true;



}
