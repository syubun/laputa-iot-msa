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

package com.laputa.iot.upms.api.feign;


import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.upms.api.dto.BaseAreaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 区域信息
 *
 * @author Sommer
 * @date 2021-09-22 09:14:19
 */
@FeignClient(contextId = "SmsRemoteService", value = ServiceNameConstants.NOTIFY_SERVICE)
public interface SmsRemoteService {




    /**
     * 通过id查询区域信息
     * @param code id
     * @return R
     */

    @PostMapping("/sms/send" )
    R sendSms(@RequestParam("mobile")String mobile, @RequestParam("code")String code);


}
