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


import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.core.base.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工
 *
 * @author Sommer
 * @date 2021-09-30 15:39:05
 */
@FeignClient(contextId = "StaffRemoteService", value = ServiceNameConstants.ORG_SERVICE)
public interface StaffRemoteService {




    /**
     * 通过id查询员工
     * @param id id
     * @return R
     */

    @GetMapping("/staff/{id}" )
    R getById(@PathVariable(value="id" ) Long id, @RequestHeader(SecurityConstants.FROM) String from);


    /**
     * 通过id查询员工
     * @param
     * @return R
     */

    @GetMapping("/staff/all" )
    R<List<Staff>> getAll();

    /**
     * 新增员工
     * @param staff 员工
     * @return R
     */

    @PostMapping("/staff")
    R save(@RequestBody Staff staff);

    /**
     * 修改员工
     * @param staff 员工
     * @return R
     */

    @PutMapping("/staff")
     R updateById(@RequestBody Staff staff);

    /**
     * 通过id删除员工
     * @param id id
     * @return R
     */

    @DeleteMapping("/staff/{id}" )
    R removeById(@PathVariable(value="id" )  Long id);
}
