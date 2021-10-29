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

package com.laputa.iot.org.api.client;


import com.laputa.iot.org.api.entity.Company;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.core.base.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 公司表
 *
 * @author Sommer
 * @date 2021-09-30 14:10:33
 */
@FeignClient(contextId = "CompanyRemoteService", value = ServiceNameConstants.ORG_SERVICE)
public interface CompanyRemoteService {





    /**
     * 通过id查询公司表
     * @param id id
     * @return R
     */

    @GetMapping("/company/{id}" )
    R getById(@PathVariable(value="id" ) Long id);

    /**
     * 新增公司表
     * @param company 公司表
     * @return R
     */

    @PostMapping("/company")
    R save(@RequestBody Company company);

    /**
     * 修改公司表
     * @param company 公司表
     * @return R
     */

    @PutMapping("/company")
     R updateById(@RequestBody Company company);

    /**
     * 通过id删除公司表
     * @param id id
     * @return R
     */

    @DeleteMapping("/company/{id}" )
    R removeById(@PathVariable(value="id" )  Long id);
}
