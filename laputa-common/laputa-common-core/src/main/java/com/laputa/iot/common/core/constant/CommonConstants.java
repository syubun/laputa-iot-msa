/*
 *
 *      Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the www.laputa-iot.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: SommerJiang (sommer_jiang@163.com)
 *
 */

package com.laputa.iot.common.core.constant;

/**
 * @author sommer.jiang
 * @date 2017/10/29
 */
public interface CommonConstants {

	/**
	 * header 中租户ID
	 */
	String TENANT_ID = "TENANT-ID";

	/**
	 * header 中版本信息
	 */
	String VERSION = "VERSION";

	/**
	 * 租户ID
	 */
	Long  TENANT_ID_1 = 1l;

	/**
	 * 删除
	 */
	String STATUS_DEL = "1";

	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	int DELETE_NORMAL = 0;

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单树根节点
	 */
	Long MENU_TREE_ROOT_ID = -1l;

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "laputa-iot-dashboard";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "laputa-iot-msa";

	/**
	 * 后端工程名
	 */
	String BACK_SERVICE_PROJECT = "laputa-";

	/**
	 * 后端工程名
	 */
	String BACK_API_PROJECT = "-api";

	String BACK_SERVICE = "-service";

	/**
	 * 公共参数
	 */
	String LAPUTA_PUBLIC_PARAM_KEY = "LAPUTA_PUBLIC_PARAM_KEY";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 0;

	/**
	 * 失败标记
	 */
	Integer FAIL = 1;

	/**
	 * 默认存储bucket
	 */
	String BUCKET_NAME = "laputa";

	/**
	 * 滑块验证码
	 */
	String IMAGE_CODE_TYPE = "blockPuzzle";

	/**
	 * 验证码开关
	 */
	String CAPTCHA_FLAG = "captcha_flag";

	/**
	 * 密码传输是否加密
	 */
	String ENC_FLAG = "enc_flag";


	/**
	 *
	 */
	public static final String TOKEN_NAME = "token";
	/**
	 *
	 */
	public static final String CATCH_KEY_USER_ID = "userid";

	public static final String CATCH_KEY_USER = "user";
	/**
	 *
	 */
	public static final String CATCH_KEY_NAME = "name";
	/**
	 *
	 */
	public static final String CATCH_KEY_ACCOUNT = "account";

	/**
	 * 组织id
	 */
	public static final String CATCH_KEY_ORG_ID = "orgid";
	/**
	 * 岗位id
	 */
	public static final String CATCH_KEY_STATION_ID = "stationid";

	/**
	 * 租户ID
	 */
	public static final String CATCH_KEY_TENANT_ID = "tenantid";

	/**
	 * 动态数据库名前缀。  每个项目配置死的
	 */
	public static final String DATABASE_NAME = "database_name";

	/**
	 * 文件分隔符
	 */
	String PATH_SPLIT = "/";

	public static final int DEL_FLAG_0 = 0;
	public static final int DEL_FLAG_1 = 1;


	Integer USER_GENDER_FEMALE = 0;
	Integer USER_GENDER_MALE = 1;

	Integer DEFAULT_USER_GENDER = USER_GENDER_MALE;
	String DEFAULT_USER_AVATAR = "/default.jpeg";
	Integer DEFAULT_USER_STATUS =1;

	String DEFAULT_LOGIN_BY_ACCOUNT = "email";

	long CHAT_MESSAGE_INSERT_ALLOW = 30L; //只接受前30分钟以内的message
	long CHAT_MESSAGE_ALIVE = 172900L; //设置每天的消息队列多久没更新后就会redis过期，48h

	int CHAT_MESSAGE_QUERY_ALLOW = 3; // 允许用户最多获取前三天的聊天记录

	int REDIS_WATCHER_EXPIRED = 30; // 用户在对话聊天框必须最多每个一段时间发送watch消息，说明自己在准备接收当前对话框的message
}
