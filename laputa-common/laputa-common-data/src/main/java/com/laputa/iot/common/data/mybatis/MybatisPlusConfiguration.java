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
	 * ?????????????????????????????????????????????????????????SQL ??????
	 * @param resolverList
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolverList) {
		resolverList.add(new SqlFilterArgumentResolver());
	}

	/**
	 * Laputa ???????????????????????????
	 * @return LaputaDefaultDatascopeHandle
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnClass(LaputaUser.class)
	public DataScopeHandle dataScopeHandle() {
		return new LaputaDefaultDatascopeHandle();
	}

	/**
	 * mybatis plus ???????????????
	 * @return LaputaDefaultDatascopeHandle
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// ???????????????
		TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
		tenantLineInnerInterceptor.setTenantLineHandler(laputaTenantHandler());
		interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
		// ????????????
		DataScopeInnerInterceptor dataScopeInnerInterceptor = new DataScopeInnerInterceptor();
		dataScopeInnerInterceptor.setDataScopeHandle(dataScopeHandle());
		interceptor.addInnerInterceptor(dataScopeInnerInterceptor);
		// ????????????
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		// ?????????????????????(?????????3.4.0)
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); // ???????????????
//		// DbType??????????????????(??????????????????????????????????????????)
//		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // ????????????


		return interceptor;
	}

	/**
	 * ?????????????????????????????????
	 * @return ?????????????????????????????????
	 */
	@Bean
	@ConditionalOnMissingBean
	public LaputaTenantHandler laputaTenantHandler() {
		return new LaputaTenantHandler();
	}

	/**
	 * ?????? mybatis-plus baseMapper ??????????????????
	 * @return
	 */
	@Bean
	@ConditionalOnBean(DataScopeHandle.class)
	public DataScopeSqlInjector dataScopeSqlInjector() {
		return new DataScopeSqlInjector();
	}

	/**
	 * SQL ???????????????
	 * @return DruidSqlLogFilter
	 */
	@Bean
	public DruidSqlLogFilter sqlLogFilter(LaputaMybatisProperties properties) {
		return new DruidSqlLogFilter(properties);
	}


	/**
	 * Mybatis Plus ?????????
	 *
	 * @param idGenerate
	 * @return
	 */
	@Bean("myMetaObjectHandler")
	public MetaObjectHandler getMyMetaObjectHandler(@Qualifier("snowflakeIdGenerate") IdGenerate<Long> idGenerate) {
		return new MyMetaObjectHandler(idGenerate);
	}

	/**
	 * id?????? ???????????? ????????????1????????? ?????????????????????????????????1?????????
	 *
	 * @param machineCode
	 * @return
	 */
	@Bean("snowflakeIdGenerate")
	public IdGenerate getIdGenerate(@Value("${id-generator.machine-code:1}") Long machineCode) {
		return new SnowflakeIdGenerate(machineCode);
	}




}
