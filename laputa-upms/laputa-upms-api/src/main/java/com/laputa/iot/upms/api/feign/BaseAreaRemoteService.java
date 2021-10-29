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


import com.laputa.iot.upms.api.dto.BaseAreaDTO;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.core.base.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 区域信息
 *
 * @author Sommer
 * @date 2021-09-22 09:14:19
 */
@FeignClient(contextId = "BaseAreaRemoteService", value = ServiceNameConstants.UPMS_SERVICE)
public interface BaseAreaRemoteService {




    /**
     * 通过id查询区域信息
     * @param code id
     * @return R
     */

    @GetMapping("/basearea/{code}" )
    R getById(@PathVariable("code" ) String code);

    /**
     * 新增区域信息
     * @param baseArea 区域信息
     * @return R
     */

    @PostMapping("/basearea")
    R save(@RequestBody BaseAreaDTO baseArea);

    /**
     * 修改区域信息
     * @param baseArea 区域信息
     * @return R
     */

    @PutMapping("/basearea")
     R updateById(@RequestBody BaseAreaDTO baseArea);

    /**
     * 通过id删除区域信息
     * @param code id
     * @return R
     */

    @DeleteMapping("/basearea/{code}" )
    R removeById(@PathVariable("code" ) String code);
}
