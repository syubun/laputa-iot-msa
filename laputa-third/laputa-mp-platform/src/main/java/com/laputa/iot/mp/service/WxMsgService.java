package com.laputa.iot.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.mp.entity.WxMsg;

/**
 * 微信消息
 *
 * @author JL
 * @date 2019-05-28 16:12:10
 */
public interface WxMsgService extends IService<WxMsg> {

	/**
	 * 保存信息并向用户推送
	 * @param wxMsg
	 * @return
	 */
	R saveAndPushMsg(WxMsg wxMsg);

}
