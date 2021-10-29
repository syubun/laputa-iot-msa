package com.laputa.iot.pay.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sommer.jiang
 * @date 2019-05-30
 * <p>
 * 订单状态
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

	/**
	 * 订单初始 下单
	 */
	INIT("0", "下单"),

	/**
	 * 订单支付成功
	 */
	SUCCESS("1", "成功"),

	/**
	 * 订单支付完成
	 */
	COMPLETE("2", "完成"),

	/**
	 * 订单支付失败
	 */
	FAIL("-1", "失败");

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 描述
	 */
	private String description;

}
