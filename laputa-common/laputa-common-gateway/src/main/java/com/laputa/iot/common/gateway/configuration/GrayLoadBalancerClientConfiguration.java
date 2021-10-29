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

package com.laputa.iot.common.gateway.configuration;

import com.laputa.iot.common.gateway.filter.GrayReactiveLoadBalancerClientFilter;
import com.laputa.iot.common.gateway.rule.GrayLoadBalancer;
import com.laputa.iot.common.gateway.rule.VersionGrayLoadBalancer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.config.GatewayReactiveLoadBalancerClientAutoConfiguration;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mica ribbon rule auto configuration.
 *
 * @author Sommer.Jiang
 * @link https://github.com/lets-mica/mica
 */
@Configuration
@EnableConfigurationProperties(LoadBalancerProperties.class)
@ConditionalOnProperty(value = "gray.rule.enabled", havingValue = "true")
@AutoConfigureBefore(GatewayReactiveLoadBalancerClientAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class GrayLoadBalancerClientConfiguration {

	@Bean
	public ReactiveLoadBalancerClientFilter gatewayLoadBalancerClientFilter(GrayLoadBalancer grayLoadBalancer,
                                                                            LoadBalancerProperties properties, GatewayLoadBalancerProperties loadBalancerProperties) {
		return new GrayReactiveLoadBalancerClientFilter(loadBalancerProperties, properties, grayLoadBalancer);
	}

	@Bean
	public GrayLoadBalancer grayLoadBalancer(DiscoveryClient discoveryClient) {
		return new VersionGrayLoadBalancer(discoveryClient);
	}

}
