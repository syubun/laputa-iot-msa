package com.laputa.iot.mp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sommer.jiang
 * @date 2020/4/26
 * <p>
 * 公众号订阅状态
 */
@Getter
@AllArgsConstructor
public enum SubStatusEnum {

	/**
	 * 已关注
	 */
	SUBED("1", "已关注"),

	/**
	 * 未关注
	 */
	UNSUB("0", "关注");

	/***
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String desc;

}
