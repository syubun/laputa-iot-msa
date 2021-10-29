package com.laputa.iot.mp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sommer.jiang
 * @date 2020/4/25
 * <p>
 * 回复类型
 */
@Getter
@AllArgsConstructor
public enum ReplyTypeEnum {

	/**
	 * 关注自动回复
	 */
	ATTENTION("1", "关注时回复"),

	/**
	 * 用户消息回复
	 */
	MSG("2", "消息回复"),

	/**
	 * 关键字回复
	 */
	KEYWORD("3", "关键词回复");

	/***
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 根据 type 查找枚举
	 * @param type
	 * @return
	 */
	public static ReplyTypeEnum getEnumByType(String type) {
		for (ReplyTypeEnum value : ReplyTypeEnum.values()) {
			if (value.getType().equals(type)) {
				return value;
			}
		}
		return null;
	}

}
