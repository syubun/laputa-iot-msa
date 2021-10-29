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

package com.laputa.iot.common.transaction.rewrite;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

/**
 * @author sommer.jiang
 * @date 2020/08/19 使用服务发现重写 Txmanager 获取规则
 */
@Slf4j
@Service
@AllArgsConstructor
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService {

	private final LoadBalancerClient loadBalancerClient;

	@Override
	public String getTxUrl() {
		ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceNameConstants.TX_MANAGER);
		String host = serviceInstance.getHost();
		Integer port = serviceInstance.getPort();
		String url = String.format("http://%s:%s/tx/manager/", host, port);
		log.info("tm.manager.url -> {}", url);
		return url;
	}

}
