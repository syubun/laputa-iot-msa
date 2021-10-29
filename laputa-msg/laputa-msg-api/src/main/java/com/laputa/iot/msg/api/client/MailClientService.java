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


import com.laputa.iot.msg.api.entity.Mail;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.core.base.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 邮件
 *
 * @author Sommer
 * @date 2021-10-20 10:13:09
 */
@FeignClient(contextId = "MailRemoteService", value = ServiceNameConstants.MSG_SERVICE)
public interface MailClientService {




    /**
     * 通过id查询邮件
     * @param id id
     * @return R
     */

//    @GetMapping("/mail/{id}" )
//    R getById(@PathVariable(value="id" ) Long id);

    /**
     * 新增邮件
     * @param mail 邮件
     * @return R
     */

    @PostMapping("/mail")
    R save(@RequestBody Mail mail);

    /**
     * 修改邮件
     * @param mail 邮件
     * @return R
     */

    @PutMapping("/mail")
     R updateById(@RequestBody Mail mail);

}
