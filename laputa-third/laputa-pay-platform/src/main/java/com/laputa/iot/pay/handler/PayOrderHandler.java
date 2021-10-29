package com.laputa.iot.pay.handler;

import com.laputa.iot.pay.entity.PayGoodsOrder;
import com.laputa.iot.pay.entity.PayChannel;
import com.laputa.iot.pay.entity.PayTradeOrder;

/**
 * @author sommer.jiang
 * @date 2019-05-31
 * <p>
 * 支付业务
 */
public interface PayOrderHandler {

	/**
	 * 准备支付参数
	 */
	default PayChannel preparePayParams() {
		return null;
	}

	/**
	 * 创建商品订单
	 * @param goodsOrder 金额
	 * @return
	 */
	void createGoodsOrder(PayGoodsOrder goodsOrder);

	/**
	 * 创建交易订单
	 * @param goodsOrder 商品订单
	 * @return
	 */
	PayTradeOrder createTradeOrder(PayGoodsOrder goodsOrder);

	/**
	 * 调起渠道支付
	 * @param goodsOrder 商品订单
	 * @param tradeOrder 交易订单
	 * @return obj
	 */
	Object pay(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder);

	/**
	 * 更新订单信息
	 * @param goodsOrder 商品订单
	 * @param tradeOrder 交易订单
	 */
	void updateOrder(PayGoodsOrder goodsOrder, PayTradeOrder tradeOrder);

	/**
	 * 调用入口
	 * @param goodsOrde 商品订单
	 * @return
	 */
	Object handle(PayGoodsOrder goodsOrde);

}
