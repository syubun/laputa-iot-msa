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

package com.laputa.iot.common.transaction.tx.springcloud.feign;

import com.codingapi.tx.aop.bean.TxTransactionLocal;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LCN on 2017/6/26.
 * @author LCN
 * @since 4.1.0
 */
@Slf4j
public class TransactionRestTemplateInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {

		TxTransactionLocal txTransactionLocal = TxTransactionLocal.current();
		String groupId = txTransactionLocal == null ? null : txTransactionLocal.getGroupId();

		log.info("LCN-SpringCloud TxGroup info -> groupId:" + groupId);

		if (txTransactionLocal != null) {
			requestTemplate.header("tx-group", groupId);
		}
	}

}
