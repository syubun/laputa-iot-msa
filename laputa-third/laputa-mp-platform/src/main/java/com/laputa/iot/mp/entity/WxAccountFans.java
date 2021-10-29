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
package com.laputa.iot.mp.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 微信公众号粉丝
 *
 * @author sommer.jiang
 * @date 2019-03-26 22:08:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxAccountFans extends Model<WxAccountFans> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;

	/**
	 * 用户标识
	 */
	private String openid;

	/**
	 * 订阅状态，0未关注，1已关注
	 */
	private String subscribeStatus;

	/**
	 * 订阅时间
	 */
	private LocalDateTime subscribeTime;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 性别，1男，2女，0未知
	 */
	private String gender;

	/**
	 * 语言
	 */
	private String language;

	/**
	 * 国家
	 */
	private String country;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 头像地址
	 */
	private String headimgUrl;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 微信公众号ID
	 */
	private Integer wxAccountId;

	/**
	 * 微信公众号appid
	 */
	private String wxAccountAppid;

	/**
	 * 微信公众号名
	 */
	private String wxAccountName;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 是否删除 -1：已删除 0：正常
	 */
	@TableLogic
	private String delFlag;

}
