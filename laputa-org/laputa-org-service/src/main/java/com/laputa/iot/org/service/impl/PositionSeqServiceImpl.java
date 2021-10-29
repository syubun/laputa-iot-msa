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
package com.laputa.iot.org.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.org.api.dto.PositionSeqDTO;
import com.laputa.iot.org.api.entity.PositionSeq;
import com.laputa.iot.org.mapper.PositionSeqMapper;
import com.laputa.iot.org.service.PositionSeqService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 岗位编码序列
 *
 * @author Sommer.Jiang
 * @date 2021-09-29 21:21:09
 */
@Service
public class PositionSeqServiceImpl extends ServiceImpl<PositionSeqMapper, PositionSeq> implements PositionSeqService {




    /**
      * 分页查询PositionSeq
      * @param page
      * @param positionSeqDTO 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, PositionSeqDTO positionSeqDTO) {
        PositionSeq positionSeq = new PositionSeq();
        BeanCopyUtil.copyProperties(positionSeqDTO, positionSeq);
        IPage<PositionSeq> selectPage = baseMapper.selectPage(page, Wrappers.query(positionSeq));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param positionSeq DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.POSITION_SEQ_KEY, key = "#positionSeq.id")
    public Boolean savePositionSeq(PositionSeq positionSeq) {

        return this.save(positionSeq);
    }


    /**
     * 通过ID查询PositionSeq
     * @param id positionSeqID
     * @return PositionSeqVO
     */
    @Override
    @Cacheable(value = CacheConstants.POSITION_SEQ_KEY, key = "#id")
    public   PositionSeq selectPositionSeqById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除PositionSeq
     * @param id positionSeqID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.POSITION_SEQ_KEY, key = "#id")
    public Boolean deletePositionSeqById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#positionSeq.id")
    public Boolean updatePositionSeq(PositionSeq positionSeq) {
        return this.updateById(positionSeq);
    }





}
