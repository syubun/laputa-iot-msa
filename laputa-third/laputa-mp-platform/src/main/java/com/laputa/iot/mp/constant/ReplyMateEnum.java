package com.laputa.iot.mp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sommer.jiang
 * @date 2020/4/25
 * <p>
 * 匹配类型
 */
@Getter
@AllArgsConstructor
public enum ReplyMateEnum {

	/**
	 * 完全匹配
	 */
	ALL("1", "完全匹配"),

	/**
	 * 半匹配
	 */
	LIKE("2", "半匹配");

	/***
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String desc;

}
