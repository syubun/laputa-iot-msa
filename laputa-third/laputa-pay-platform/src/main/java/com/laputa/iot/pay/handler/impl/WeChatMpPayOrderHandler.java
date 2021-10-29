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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import com.laputa.iot.common.data.tenant.TenantContextHolder;
import com.laputa.iot.pay.entity.PayGoodsOrder;
import com.laputa.iot.pay.entity.PayChannel;
import com.laputa.iot.pay.entity.PayTradeOrder;
import com.laputa.iot.pay.mapper.PayChannelMapper;
import com.laputa.iot.pay.mapper.PayGoodsOrderMapper;
import com.laputa.iot.pay.mapper.PayTradeOrderMapper;
import com.laputa.iot.pay.utils.ChannelPayApiConfigKit;
import com.laputa.iot.pay.utils.OrderStatusEnum;
import com.laputa.iot.pay.utils.PayChannelNameEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sommer.jiang
 * @date 2019-05-31
 * <p>
 * 微信公众号支付
 */
@Slf4j
@Service("WEIXIN_MP")
@RequiredArgsConstructor
public class WeChatMpPayOrderHandler extends AbstractPayOrderHandler {

	private final PayTradeOrderMapper tradeOrderMapper;

	private final PayGoodsOrderMapper goodsOrderMapper;

	private final PayChannelMapper channelMapper;

	private final HttpServletRequest request;

	/**
	 * 准备支付参数
	 * @return PayChannel
	 */
	@Override
	public PayChannel preparePayParams() {
		PayChannel channel = channelMapper.selectOne(
				Wrappers.<PayChannel>lambdaQuery().eq(PayChannel::getChannelId, PayChannelNameEnum.WEIXIN_MP.name()));

		if (channel == null) {
			throw new IllegalArgumentException("微信公众号支付渠道配置为空");
		}

		JSONObject params = JSONUtil.parseObj(channel.getParam());
		WxPayApiConfig wx = WxPayApiConfig.builder().appId(channel.getAppId()).mchId(channel.getChannelMchId())
				.partnerKey(params.getStr("partnerKey")).build();

		WxPayApiConfigKit.putApiConfig(wx);
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
		tradeOrder.setChannelId(PayChannelNameEnum.WEIXIN_MP.getName());
		tradeOrder.setChannelMchId(WxPayApiConfigKit.getWxPayApiConfig().getMchId());
		tradeOrder.setClientIp(ServletUtil.getClientIP(request));
		tradeOrder.setCurrency("CNY");
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
	public Object pay(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder) {
		String ip = ServletUtil.getClientIP(request);
		WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

		// 预订单参数
		Map<String, String> params = UnifiedOrderModel.builder().appid(wxPayApiConfig.getAppId())
				.mch_id(wxPayApiConfig.getMchId()).nonce_str(WxPayKit.generateStr()).body(goodsOrder.getGoodsName())
				.attach(TenantContextHolder.getTenantId().toString()).out_trade_no(tradeOrder.getOrderId())
				.total_fee(goodsOrder.getAmount()).spbill_create_ip(ip)
				.notify_url(ChannelPayApiConfigKit.get().getNotifyUrl() + "/pay/notify/wx/callbak")
				.trade_type(TradeType.JSAPI.getTradeType()).openid(goodsOrder.getUserId()).build()
				.createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

		String xmlResult = WxPayApi.pushOrder(false, params);
		log.info("微信统一下单返回 {}", xmlResult);
		Map<String, String> resultMap = WxPayKit.xmlToMap(xmlResult);
		String prepayId = resultMap.get("prepay_id");
		return WxPayKit.prepayIdCreateSign(prepayId, wxPayApiConfig.getAppId(), wxPayApiConfig.getPartnerKey(),
				SignType.HMACSHA256);
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
