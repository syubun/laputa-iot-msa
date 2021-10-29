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

package com.laputa.iot.common.data.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.laputa.iot.common.core.base.id.IdGenerate;
import com.laputa.iot.common.core.base.id.SnowflakeIdGenerate;
import com.laputa.iot.common.data.config.LaputaMybatisProperties;
import com.laputa.iot.common.data.datascope.DataScopeInnerInterceptor;
import com.laputa.iot.common.data.datascope.DataScopeSqlInjector;
import com.laputa.iot.common.data.datascope.LaputaDefaultDatascopeHandle;
import com.laputa.iot.common.data.tenant.LaputaTenantHandler;
import com.laputa.iot.common.data.datascope.DataScopeHandle;
import com.laputa.iot.common.data.resolver.SqlFilterArgumentResolver;
import com.laputa.iot.common.security.service.LaputaUser;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author sommer.jiang
 * @date 2020-02-08
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(LaputaMybatisProperties.class)
public class MybatisPlusConfiguration implements WebMvcConfigurer {

	/**
	 * 增加请求参数解析器，对请求中的参数注入SQL 检查
	 * @param resolverList
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolverList) {
		resolverList.add(new SqlFilterArgumentResolver());
	}

	/**
	 * Laputa 默认数据权限处理器
	 * @return LaputaDefaultDatascopeHandle
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnClass(LaputaUser.class)
	public DataScopeHandle dataScopeHandle() {
		return new LaputaDefaultDatascopeHandle();
	}

	/**
	 * mybatis plus 拦截器配置
	 * @return LaputaDefaultDatascopeHandle
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// 多租户支持
		TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
		tenantLineInnerInterceptor.setTenantLineHandler(laputaTenantHandler());
		interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
		// 数据权限
		DataScopeInnerInterceptor dataScopeInnerInterceptor = new DataScopeInnerInterceptor();
		dataScopeInnerInterceptor.setDataScopeHandle(dataScopeHandle());
		interceptor.addInnerInterceptor(dataScopeInnerInterceptor);
		// 分页支持
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		// 注册乐观锁插件(新版：3.4.0)
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); // 乐观锁插件
//		// DbType：数据库类型(根据类型获取应使用的分页方言)
//		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 分页插件


		return interceptor;
	}

	/**
	 * 创建租户维护处理器对象
	 * @return 处理后的租户维护处理器
	 */
	@Bean
	@ConditionalOnMissingBean
	public LaputaTenantHandler laputaTenantHandler() {
		return new LaputaTenantHandler();
	}

	/**
	 * 扩展 mybatis-plus baseMapper 支持数据权限
	 * @return
	 */
	@Bean
	@ConditionalOnBean(DataScopeHandle.class)
	public DataScopeSqlInjector dataScopeSqlInjector() {
		return new DataScopeSqlInjector();
	}

	/**
	 * SQL 日志格式化
	 * @return DruidSqlLogFilter
	 */
	@Bean
	public DruidSqlLogFilter sqlLogFilter(LaputaMybatisProperties properties) {
		return new DruidSqlLogFilter(properties);
	}


	/**
	 * Mybatis Plus 注入器
	 *
	 * @param idGenerate
	 * @return
	 */
	@Bean("myMetaObjectHandler")
	public MetaObjectHandler getMyMetaObjectHandler(@Qualifier("snowflakeIdGenerate") IdGenerate<Long> idGenerate) {
		return new MyMetaObjectHandler(idGenerate);
	}

	/**
	 * id生成 机器码， 单机配置1即可。 集群部署，每个实例自增1即可。
	 *
	 * @param machineCode
	 * @return
	 */
	@Bean("snowflakeIdGenerate")
	public IdGenerate getIdGenerate(@Value("${id-generator.machine-code:1}") Long machineCode) {
		return new SnowflakeIdGenerate(machineCode);
	}




}
