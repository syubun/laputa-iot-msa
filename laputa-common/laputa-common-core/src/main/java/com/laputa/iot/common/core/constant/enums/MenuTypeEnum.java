package com.laputa.iot.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sommer.jiang
 * @date 2020-02-17
 * <p>
 * 菜单类型
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

	/**
	 * 左侧菜单
	 */
	MENU_PATH(0, "path"),

	/**
	 * 顶部菜单
	 */
	MENU_ROUTER(1, "router"),

	/**
	 * 按钮
	 */
	BUTTON(2, "button");

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 描述
	 */
	private String description;

}
