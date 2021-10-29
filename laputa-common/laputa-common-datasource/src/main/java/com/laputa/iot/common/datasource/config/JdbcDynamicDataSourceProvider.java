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

package com.laputa.iot.common.datasource.config;

import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.laputa.iot.common.datasource.support.DataSourceConstants;
import com.laputa.iot.common.datasource.util.DsConfTypeEnum;
import com.laputa.iot.common.datasource.util.DsJdbcUrlEnum;
import org.jasypt.encryption.StringEncryptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sommer.jiang
 * @date 2020/2/6
 * <p>
 * 从数据源中获取 配置信息
 */
public class JdbcDynamicDataSourceProvider extends AbstractJdbcDataSourceProvider {

	private final DruidDataSourceProperties properties;

	private final StringEncryptor stringEncryptor;

	public JdbcDynamicDataSourceProvider(StringEncryptor stringEncryptor, DruidDataSourceProperties properties) {
		super(properties.getDriverClassName(), properties.getUrl(), properties.getUsername(), properties.getPassword());
		this.stringEncryptor = stringEncryptor;
		this.properties = properties;
	}

	/**
	 * 执行语句获得数据源参数
	 * @param statement 语句
	 * @return 数据源参数
	 * @throws SQLException sql异常
	 */
	@Override
	protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
		ResultSet rs = statement.executeQuery(properties.getQueryDsSql());

		Map<String, DataSourceProperty> map = new HashMap<>(8);
		while (rs.next()) {
			String name = rs.getString(DataSourceConstants.NAME);
			String username = rs.getString(DataSourceConstants.DS_USER_NAME);
			String password = rs.getString(DataSourceConstants.DS_USER_PWD);
			Integer confType = rs.getInt(DataSourceConstants.DS_CONFIG_TYPE);
			String dsType = rs.getString(DataSourceConstants.DS_TYPE);

			String url;
			// JDBC 配置形式
			DsJdbcUrlEnum urlEnum = DsJdbcUrlEnum.get(dsType);
			if (DsConfTypeEnum.JDBC.getType().equals(confType)) {
				url = rs.getString(DataSourceConstants.DS_JDBC_URL);
			}
			else if (DsJdbcUrlEnum.MSSQL.getDbName().equals(dsType)) {
				String host = rs.getString(DataSourceConstants.DS_HOST);
				String port = rs.getString(DataSourceConstants.DS_PORT);
				String dsName = rs.getString(DataSourceConstants.DS_NAME);
				String instance = rs.getString(DataSourceConstants.DS_INSTANCE);
				url = String.format(urlEnum.getUrl(), host, instance, port, dsName);
			}
			else {
				String host = rs.getString(DataSourceConstants.DS_HOST);
				String port = rs.getString(DataSourceConstants.DS_PORT);
				String dsName = rs.getString(DataSourceConstants.DS_NAME);
				url = String.format(urlEnum.getUrl(), host, port, dsName);
			}
//			System.out.println(password);
			DataSourceProperty property = new DataSourceProperty();
			property.setUsername(username);
			property.setPassword(stringEncryptor.decrypt(password));
			property.setUrl(url);
			map.put(name, property);
		}

		// 添加默认主数据源
		DataSourceProperty property = new DataSourceProperty();
		property.setUsername(properties.getUsername());
		property.setPassword(properties.getPassword());
		property.setUrl(properties.getUrl());
		map.put(DataSourceConstants.DS_MASTER, property);
		return map;
	}

}
