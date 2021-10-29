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

package com.laputa.iot.pay.handler.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ijpay.alipay.AliPayApi;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import com.laputa.iot.common.data.tenant.TenantContextHolder;
import com.laputa.iot.pay.entity.PayGoodsOrder;
import com.laputa.iot.pay.utils.ChannelPayApiConfigKit;
import com.laputa.iot.pay.entity.PayChannel;
import com.laputa.iot.pay.entity.PayTradeOrder;
import com.laputa.iot.pay.mapper.PayChannelMapper;
import com.laputa.iot.pay.mapper.PayGoodsOrderMapper;
import com.laputa.iot.pay.mapper.PayTradeOrderMapper;
import com.laputa.iot.pay.utils.OrderStatusEnum;
import com.laputa.iot.pay.utils.PayChannelNameEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sommer.jiang
 * @date 2019-05-31
 * <p>
 * 支付宝手机支付
 */
@Slf4j
@Service("ALIPAY_WAP")
@AllArgsConstructor
public class AlipayWapPayOrderHandler extends AbstractPayOrderHandler {

	private final PayTradeOrderMapper tradeOrderMapper;

	private final PayGoodsOrderMapper goodsOrderMapper;

	private final PayChannelMapper channelMapper;

	private final HttpServletRequest request;

	private final HttpServletResponse response;

	/**
	 * 准备支付参数
	 */
	@Override
	public PayChannel preparePayParams() {
		PayChannel channel = channelMapper.selectOne(
				Wrappers.<PayChannel>lambdaQuery().eq(PayChannel::getChannelId, PayChannelNameEnum.ALIPAY_WAP.name()));

		if (channel == null) {
			throw new IllegalArgumentException("支付宝网页支付渠道配置为空");
		}

		JSONObject params = JSONUtil.parseObj(channel.getParam());
		AliPayApiConfig aliPayApiConfig = AliPayApiConfig.builder().setAppId(channel.getAppId())
				.setPrivateKey(params.getStr("privateKey")).setCharset(CharsetUtil.UTF_8)
				.setAliPayPublicKey(params.getStr("publicKey")).setServiceUrl(params.getStr("serviceUrl"))
				.setSignType("RSA2").build();
		AliPayApiConfigKit.putApiConfig(aliPayApiConfig);
		return channel;
	}

	/**
	 * 创建交易订单
	 * @param goodsOrder
	 * @return
	 */
	@Override
	public PayTradeOrder createTradeOrder(PayGoodsOrder goodsOrder) {
		PayTradeOrder tradeOrder = new PayTradeOrder();
		tradeOrder.setOrderId(goodsOrder.getPayOrderId());
		tradeOrder.setAmount(goodsOrder.getAmount());
		tradeOrder.setChannelId(PayChannelNameEnum.ALIPAY_WAP.getName());
		tradeOrder.setChannelMchId(AliPayApiConfigKit.getAliPayApiConfig().getAppId());
		tradeOrder.setClientIp(ServletUtil.getClientIP(request));
		tradeOrder.setCurrency("cny");
		tradeOrder.setExpireTime(30L);
		tradeOrder.setStatus(OrderStatusEnum.INIT.getStatus());
		tradeOrder.setBody(goodsOrder.getGoodsName());
		tradeOrderMapper.insert(tradeOrder);
		return tradeOrder;
	}

	/**
	 * 调起渠道支付
	 * @param goodsOrder 商品订单
	 * @param tradeOrder 交易订单
	 */
	@Override
	public PayTradeOrder pay(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder) {
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setBody(tradeOrder.getBody());
		model.setSubject(tradeOrder.getBody());
		model.setOutTradeNo(tradeOrder.getOrderId());
		model.setTimeoutExpress("30m");

		// 分转成元 并且保留两位
		model.setTotalAmount(NumberUtil.div(tradeOrder.getAmount(), "100", 2).toString());
		model.setProductCode(goodsOrder.getGoodsId());
		model.setPassbackParams(String.valueOf(TenantContextHolder.getTenantId()));
		try {
			log.info("拉起支付宝wap 支付参数 {}", model);
			AliPayApi.wapPay(response, model, ChannelPayApiConfigKit.get().getReturnUrl(),
					ChannelPayApiConfigKit.get().getNotifyUrl() + "/pay/notify/ali/callbak");
		}
		catch (AlipayApiException e) {
			log.error("支付宝手机支付失败", e);
			tradeOrder.setErrMsg(e.getErrMsg());
			tradeOrder.setErrCode(e.getErrCode());
			tradeOrder.setStatus(OrderStatusEnum.FAIL.getStatus());
			goodsOrder.setStatus(OrderStatusEnum.FAIL.getStatus());
		}
		catch (IOException e) {
			log.error("支付宝手机支付失败", e);
			tradeOrder.setErrMsg(e.getMessage());
			tradeOrder.setStatus(OrderStatusEnum.FAIL.getStatus());
			goodsOrder.setStatus(OrderStatusEnum.FAIL.getStatus());
		}
		return tradeOrder;
	}

	/**
	 * 更新订单信息
	 * @param goodsOrder 商品订单
	 * @param tradeOrder 交易订单
	 */
	@Override
	public void updateOrder(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder) {
		tradeOrderMapper.updateById(tradeOrder);
		goodsOrderMapper.updateById(goodsOrder);
	}

}
