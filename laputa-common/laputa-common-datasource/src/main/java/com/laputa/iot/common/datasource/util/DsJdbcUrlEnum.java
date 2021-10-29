package com.laputa.iot.common.datasource.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author sommer.jiang
 * @date 2020/12/11
 * <p>
 * jdbc-url
 */
@Getter
@AllArgsConstructor
public enum DsJdbcUrlEnum {

	/**
	 * mysql 数据库
	 */
	MYSQL("mysql",
			"jdbc:mysql://%s:%s/%s?characterEncoding=utf8"
					+ "&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true",
			"mysql8 链接"),

	/**
	 * pg 数据库
	 */
	PG("pg", "jdbc:postgresql://%s:%s/%s", "postgresql 链接"),

	/**
	 * SQL SERVER
	 */
	MSSQL("mssql", "jdbc:sqlserver://%s\\%s:%s;database=%s;characterEncoding=UTF-8", "sqlserver 链接"),

	/**
	 * oracle
	 */
	ORACLE("oracle", "jdbc:oracle:thin:@%s:%s:%s", "oracle 链接"),

	/**
	 * db2
	 */
	DB2("db2", "jdbc:db2://%s:%s/%s", "DB2 TYPE4 连接");

	private final String dbName;

	private final String url;

	private final String description;

	public static DsJdbcUrlEnum get(String dsType) {
		return Arrays.stream(DsJdbcUrlEnum.values()).filter(dsJdbcUrlEnum -> dsType.equals(dsJdbcUrlEnum.getDbName()))
				.findFirst().get();
	}

}
