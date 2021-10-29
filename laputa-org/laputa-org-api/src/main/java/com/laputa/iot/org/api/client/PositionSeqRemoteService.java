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


import com.laputa.iot.org.api.entity.PositionSeq;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.core.base.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 岗位编码序列
 *
 * @author Sommer.Jiang
 * @date 2021-09-29 21:21:09
 */
@FeignClient(contextId = "PositionSeqRemoteService", value = ServiceNameConstants.ORG_SERVICE)
public interface PositionSeqRemoteService {





    /**
     * 通过id查询岗位编码序列
     * @param id id
     * @return R
     */

    @GetMapping("/positionseq/{id}" )
    R getById(@PathVariable(value="id")Long id);

    /**
     * 新增岗位编码序列
     * @param positionSeq 岗位编码序列
     * @return R
     */

    @PostMapping("/positionseq")
    R save(@RequestBody PositionSeq positionSeq);

    /**
     * 修改岗位编码序列
     * @param positionSeq 岗位编码序列
     * @return R
     */

    @PutMapping("/positionseq")
     R updateById(@RequestBody PositionSeq positionSeq);

    /**
     * 通过id删除岗位编码序列
     * @param id id
     * @return R
     */

    @DeleteMapping("/positionseq/{id}" )
    R removeById(@PathVariable(value="id") Long id);
}
