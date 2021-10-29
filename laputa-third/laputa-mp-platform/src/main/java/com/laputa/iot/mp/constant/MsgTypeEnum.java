package com.laputa.iot.mp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sommer.jiang
 * @date 2020/4/25
 * <p>
 * 消息类型
 */
@Getter
@AllArgsConstructor
public enum MsgTypeEnum {

	/**
	 * 用户发给公众号
	 */
	USER2MP("1", "用户发给公众号"),

	/**
	 * 公众号发给用户
	 */
	MP2USER("2", "公众号发给用户");

	/***
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String desc;

}
