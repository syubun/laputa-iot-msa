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


import com.laputa.iot.org.api.entity.Role;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.core.base.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 公司角色
 *
 * @author Sommer
 * @date 2021-09-29 22:08:34
 */
@FeignClient(contextId = "RoleRemoteService", value = ServiceNameConstants.ORG_SERVICE)
public interface RoleRemoteService {




    /**
     * 通过id查询公司角色
     * @param id id
     * @return R
     */

    @GetMapping("/role/{id}" )
    R getById(@PathVariable(value="id") Long id);

    /**
     * 新增公司角色
     * @param role 公司角色
     * @return R
     */

    @PostMapping("/role")
    R save(@RequestBody Role role);

    /**
     * 修改公司角色
     * @param role 公司角色
     * @return R
     */

    @PutMapping("/role")
     R updateById(@RequestBody Role role);

    /**
     * 通过id删除公司角色
     * @param id id
     * @return R
     */

    @DeleteMapping("/role/{id}" )
    R removeById(@PathVariable(value="id") Long id);
}
