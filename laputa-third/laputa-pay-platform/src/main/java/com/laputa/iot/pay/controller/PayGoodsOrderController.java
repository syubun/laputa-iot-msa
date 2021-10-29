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

package com.laputa.iot.pay.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.data.tenant.TenantContextHolder;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.common.security.annotation.Inner;
import com.laputa.iot.pay.entity.PayGoodsOrder;
import com.laputa.iot.pay.service.PayChannelService;
import com.laputa.iot.pay.utils.PayConstants;
import com.laputa.iot.pay.entity.PayChannel;
import com.laputa.iot.pay.service.PayGoodsOrderService;
import com.laputa.iot.pay.utils.PayChannelNameEnum;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商品
 *
 * @author sommer.jiang
 * @date 2019-05-28 23:58:27
 */
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/goods")
@Api(value = "goods", tags = "商品订单管理")
public class PayGoodsOrderController {

	private final PayGoodsOrderService payGoodsOrderService;

	private final PayChannelService channelService;

	private final ObjectMapper objectMapper;

	/**
	 * 商品订单
	 * @param goods 商品
	 * @param response
	 *
	 * AliPayApiConfigKit.setAppId WxPayApiConfigKit.setAppId shezhi
	 *
	 */
	@SneakyThrows
	@Inner(false)
	@GetMapping("/buy")
	@SysLog("购买商品")
	public void buy(PayGoodsOrder goods, HttpServletRequest request, HttpServletResponse response) {
		String ua = request.getHeader(HttpHeaders.USER_AGENT);
		log.info("当前扫码方式 UA:{}", ua);

		if (ua.contains(PayConstants.MICRO_MESSENGER)) {
			PayChannel channel = channelService.getOne(
					Wrappers.<PayChannel>lambdaQuery().eq(PayChannel::getChannelId, PayChannelNameEnum.WEIXIN_MP),
					false);

			if (channel == null) {
				throw new IllegalArgumentException("公众号支付配置不存在");
			}

			String wxUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s"
					+ "&redirect_uri=%s&response_type=code&scope=snsapi_base&state=%s";

			String redirectUri = String.format("%s/pay/goods/wx?amount=%s&TENANT-ID=%s", channel.getNotifyUrl(),
					goods.getAmount(), TenantContextHolder.getTenantId());

			response.sendRedirect(
					String.format(wxUrl, channel.getAppId(), URLUtil.encode(redirectUri), channel.getAppId()));
		}

		if (ua.contains(PayConstants.ALIPAY)) {
			payGoodsOrderService.buy(goods, false);
		}

	}

	@SneakyThrows
	@Inner(false)
	@GetMapping("/merge/buy")
	@SysLog("聚合支付购买商品")
	public void mergeBuy(PayGoodsOrder goods, HttpServletResponse response) {
		Map<String, Object> result = payGoodsOrderService.buy(goods, true);
		response.setContentType(ContentType.JSON.getValue());
		response.getWriter().print(objectMapper.writeValueAsString(result));
	}

	/**
	 * oauth
	 * @param goods 商品信息
	 * @param code 回调code
	 * @param modelAndView
	 * @return
	 * @throws WxErrorException
	 */
	@Inner(false)
	@SneakyThrows
	@GetMapping("/wx")
	public ModelAndView wx(PayGoodsOrder goods, String code, ModelAndView modelAndView) {
		PayChannel channel = channelService.getOne(
				Wrappers.<PayChannel>lambdaQuery().eq(PayChannel::getChannelId, PayChannelNameEnum.WEIXIN_MP), false);

		if (channel == null) {
			throw new IllegalArgumentException("公众号支付配置不存在");
		}

		JSONObject params = JSONUtil.parseObj(channel.getParam());
		WxMpService wxMpService = new WxMpServiceImpl();
		WxMpDefaultConfigImpl storage = new WxMpDefaultConfigImpl();
		storage.setAppId(channel.getAppId());
		storage.setSecret(params.getStr("secret"));
		wxMpService.setWxMpConfigStorage(storage);

		WxMpOAuth2AccessToken mpOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
		goods.setUserId(mpOAuth2AccessToken.getOpenId());
		goods.setAmount(goods.getAmount());
		modelAndView.setViewName("pay");
		modelAndView.addAllObjects(payGoodsOrderService.buy(goods, false));
		return modelAndView;
	}

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param payGoodsOrder 商品订单表
	 * @return
	 */
	@ResponseBody
	@GetMapping("/page")
	public R getPayGoodsOrderPage(Page page, PayGoodsOrder payGoodsOrder) {
		return R.ok(payGoodsOrderService.page(page, Wrappers.query(payGoodsOrder)));
	}

	/**
	 * 通过id查询商品订单表
	 * @param goodsOrderId id
	 * @return R
	 */
	@ResponseBody
	@GetMapping(value = "/{goodsOrderId}")
	public R getById(@PathVariable("goodsOrderId") Integer goodsOrderId) {
		return R.ok(payGoodsOrderService.getById(goodsOrderId));
	}

	/**
	 * 新增商品订单表
	 * @param payGoodsOrder 商品订单表
	 * @return R
	 */
	@SysLog("新增商品订单表")
	@PostMapping
	@ResponseBody
	@PreAuthorize("@pms.hasPermission('generator_paygoodsorder_add')")
	public R save(@RequestBody PayGoodsOrder payGoodsOrder) {
		return R.ok(payGoodsOrderService.save(payGoodsOrder));
	}

	/**
	 * 修改商品订单表
	 * @param payGoodsOrder 商品订单表
	 * @return R
	 */
	@SysLog("修改商品订单表")
	@PutMapping
	@ResponseBody
	@PreAuthorize("@pms.hasPermission('generator_paygoodsorder_edit')")
	public R updateById(@RequestBody PayGoodsOrder payGoodsOrder) {
		return R.ok(payGoodsOrderService.updateById(payGoodsOrder));
	}

	/**
	 * 通过id删除商品订单表
	 * @param goodsOrderId id
	 * @return R
	 */
	@SysLog("删除商品订单表")
	@ResponseBody
	@DeleteMapping("/{goodsOrderId}")
	@PreAuthorize("@pms.hasPermission('generator_paygoodsorder_del')")
	public R removeById(@PathVariable Integer goodsOrderId) {
		return R.ok(payGoodsOrderService.removeById(goodsOrderId));
	}

}
