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
 * @date 2018年06月22日16:41:01 服务名称
 */
public interface ServiceNameConstants {

	/**
	 * 认证中心
	 */
	String AUTH_SERVICE = "laputa-auth";

	/**
	 * UMPS服务
	 */
	String UPMS_SERVICE = "laputa-upms-service";

	/**
	 * ORG 组织服务
	 */
	String ORG_SERVICE = "laputa-org-service";
	/**
	 * LOG服务
	 */
	String LOG_SERVICE = "laputa-log-service";

	/**
	 * PAY服务
	 */
	String PAY_SERVICE = "laputa-pay-service";

	/**
	 * WORKFLOW服务
	 */
	String WORKFLOW_SERVICE = "laputa-workflow-service";

	/**
	 * DEVICE服务
	 */
	String DEVICE_SERVICE = "laputa-device-service";

	/**
	 * MESSAGE服务
	 */
	String MSG_SERVICE = "laputa-msg-service";

	/**
	 * 分布式事务协调服务
	 */
	String TX_MANAGER = "laputa-tx-manager";

    String NOTIFY_SERVICE =  "laputa-notify-platform";
}
