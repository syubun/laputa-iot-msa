package com.laputa.iot.common.datasource.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sommer.jiang
 * @date 2020/12/11
 * <p>
 * 数据源配置类型
 */
@Getter
@AllArgsConstructor
public enum DsConfTypeEnum {

	/**
	 * 主机链接
	 */
	HOST(0, "主机链接"),

	/**
	 * JDBC链接
	 */
	JDBC(1, "JDBC链接");

	private final Integer type;

	private final String description;

}
