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

package com.laputa.iot.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laputa.iot.upms.api.entity.SysTenant;

import java.util.List;

/**
 * 租户管理
 *
 * @author sommer.jiang
 * @date 2019-05-15 15:55:41
 */
public interface SysTenantService extends IService<SysTenant> {

	/**
	 * 获取正常的租户
	 * @return
	 */
	List<SysTenant> getNormalTenant();

	/**
	 * 保存租户
	 * @param sysTenant
	 * @return
	 */
	Boolean saveTenant(SysTenant sysTenant);

}
