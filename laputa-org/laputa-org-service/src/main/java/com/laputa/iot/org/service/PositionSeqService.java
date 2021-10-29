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

package com.laputa.iot.org.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laputa.iot.org.api.dto.PositionSeqDTO;
import com.laputa.iot.org.api.entity.PositionSeq;

/**
 * 岗位编码序列
 *
 * @author Sommer.Jiang
 * @date 2021-09-29 21:21:09
 */
public interface PositionSeqService extends IService<PositionSeq> {



    /**
     * 分页查询PositionSeq
     * @param page 分页对象
     * @param positionSeqDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, PositionSeqDTO positionSeqDTO);

    /**
     * 删除PositionSeq
     * @param id
     * @return boolean
     */
    Boolean deletePositionSeqById(Long id);

    /**
     * 更新指定PositionSeq
     * @param positionSeq PositionSeq信息
     * @return
     */
    Boolean updatePositionSeq(PositionSeq positionSeq);

    /**
     * 通过ID查询PositionSeq
     * @param id positionSeqID
     * @return PositionSeqVO
     */
    PositionSeq selectPositionSeqById(Long id);



    /**
     * 保存PositionSeq
     * @param positionSeq DTO 对象
     * @return success/fail
     */
    Boolean savePositionSeq(PositionSeq positionSeq);


}
