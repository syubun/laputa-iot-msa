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

package com.laputa.iot.msg.api.client;


import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.msg.api.entity.Mail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 短消息
 *
 * @author Sommer
 * @date 2021-10-20 10:13:09
 */
@FeignClient(contextId = "SmsClientService", value = ServiceNameConstants.MSG_SERVICE)
public interface SmsClientService {





    /**
     * 发送验证码
     * @param mobile 手机号码 验证号
     * @param code 验证号
     * @return R
     */

    @PostMapping("/sms/send")
    R send(@RequestParam(value = "mobile", required = false) String mobile,@RequestParam(value = "code") String code);



}
