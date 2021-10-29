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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.org.api.entity.PositionInfo;
import com.laputa.iot.org.api.dto.PositionInfoDTO;
import com.laputa.iot.org.api.entity.PositionSeq;
import com.laputa.iot.org.api.vo.OrgTreeVo;
import com.laputa.iot.org.mapper.PositionInfoMapper;
import com.laputa.iot.org.service.PositionInfoService;
import com.laputa.iot.org.service.PositionSeqService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 岗位
 *
 * @author Sommer
 * @date 2021-10-03 12:04:34
 */
@Service
public class PositionInfoServiceImpl extends ServiceImpl<PositionInfoMapper, PositionInfo> implements PositionInfoService {

    @Autowired
    private PositionSeqService positionSeqService;


    /**
      * 分页查询PositionInfo
      * @param page
      * @param positionInfoDTO 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, PositionInfoDTO positionInfoDTO) {
        PositionInfo positionInfo = new PositionInfo();
        BeanCopyUtil.copyProperties(positionInfo,positionInfo);
        IPage<PositionInfo> selectPage = baseMapper.selectPage(page, Wrappers.query(positionInfo));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param positionInfo DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.POSITION_INFO_KEY, key = "#positionInfo.id")
    public Boolean savePositionInfo(PositionInfo positionInfo) {

        return this.save(positionInfo);
    }


    /**
     * 通过ID查询PositionInfo
     * @param id positionInfoID
     * @return PositionInfoVO
     */
    @Override
    @Cacheable(value = CacheConstants.POSITION_INFO_KEY, key = "#id")
    public   PositionInfo selectPositionInfoById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除PositionInfo
     * @param id positionInfoID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.POSITION_INFO_KEY, key = "#id")
    public Boolean deletePositionInfoById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#positionInfo.id")
    public Boolean updatePositionInfo(PositionInfo positionInfo) {
        return this.updateById(positionInfo);
    }


    @Override
    public List<OrgTreeVo> getPositionTree() {
        List<OrgTreeVo> orgTreeVos = new ArrayList<>();
        LambdaQueryWrapper<PositionSeq> positionSeqLambdaQueryWrapper = new LambdaQueryWrapper<>();
        positionSeqLambdaQueryWrapper.orderByAsc(PositionSeq::getOrderNo);
        List<PositionSeq> positionSeqs = positionSeqService.list(positionSeqLambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(positionSeqs)){
            positionSeqs.forEach(positionSeq -> {
                OrgTreeVo orgTreeVo = new OrgTreeVo();
                orgTreeVo.setId(positionSeq.getId());
                orgTreeVo.setPid(positionSeq.getPid());
                orgTreeVo.setCode(positionSeq.getCode());
                orgTreeVo.setName(positionSeq.getName());
                orgTreeVo.setSourceType("1");
                orgTreeVos.add(orgTreeVo);
            });
        }
        Map<Long, PositionSeq> stringPositionSeqMap = positionSeqs.stream().collect(Collectors.toMap(PositionSeq::getId, positionSeq -> positionSeq));
        LambdaQueryWrapper<PositionInfo> positionInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        positionInfoLambdaQueryWrapper.orderByAsc(PositionInfo::getOrderNo);
        List<PositionInfo> positionInfos = this.list(positionInfoLambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(positionSeqs)){
            positionInfos.forEach(positionInfo -> {
                OrgTreeVo orgTreeVo = new OrgTreeVo();
                orgTreeVo.setId(positionInfo.getId());
                if (!positionInfo.getPositionSeqId().equals(CommonConstants.MENU_TREE_ROOT_ID)){
                    PositionSeq positionSeq = stringPositionSeqMap.get(positionInfo.getPositionSeqId());
                    orgTreeVo.setPid(positionSeq.getId());
                }
                orgTreeVo.setCode(positionInfo.getCode());
                orgTreeVo.setName(positionInfo.getName());
                orgTreeVo.setSourceType("2");
                orgTreeVos.add(orgTreeVo);
            });
        }
        return orgTreeVos;
    }


}
