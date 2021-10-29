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

package com.laputa.iot.pay.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laputa.iot.pay.entity.PayGoodsOrder;

/**
 * 商品
 *
 * @author sommer.jiang
 * @date 2019-05-28 23:58:27
 */
public interface PayGoodsOrderService extends IService<PayGoodsOrder> {

	/**
	 * 购买商品
	 * @param goodsOrder goods
	 * @param isMerge 是否是服务商
	 * @return
	 */
	Map<String, Object> buy(PayGoodsOrder goodsOrder, boolean isMerge);

}
