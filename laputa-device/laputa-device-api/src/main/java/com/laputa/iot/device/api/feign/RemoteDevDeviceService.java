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

package com.laputa.iot.device.api.feign;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.device.api.dto.DevDeviceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author sommer.jiang
 * @date 2018/6/22
 */
@FeignClient(contextId = "remoteDeviceService", value = ServiceNameConstants.DEVICE_SERVICE)
public interface RemoteDevDeviceService {

	/**
	 * 分页查询设备列表
	 *
	 * @param page
	 * @param devDevice
	 * @return
	 */
	@GetMapping("/devdevice/page")
	R<DevDeviceDto> getDevDevicePage(@RequestParam("page") Page page, @RequestParam("page")  DevDeviceDto devDevice);




}
