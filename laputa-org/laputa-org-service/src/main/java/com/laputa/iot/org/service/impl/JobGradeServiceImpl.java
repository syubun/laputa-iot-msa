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
import com.laputa.iot.org.api.entity.JobGrade;
import com.laputa.iot.org.api.dto.JobGradeDTO;
import com.laputa.iot.org.api.entity.JobGradeType;
import com.laputa.iot.org.api.vo.OrgTreeVo;
import com.laputa.iot.org.mapper.JobGradeMapper;
import com.laputa.iot.org.service.JobGradeService;
import com.laputa.iot.org.service.JobGradeTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 工作等级表
 *
 * @author Sommer
 * @date 2021-10-03 11:35:01
 */
@Service
public class JobGradeServiceImpl extends ServiceImpl<JobGradeMapper, JobGrade> implements JobGradeService {

    @Autowired
    private JobGradeTypeService jobGradeTypeService;

    @Override
    public List<OrgTreeVo> getJobGradeTree() {
        List<OrgTreeVo> orgTreeVos = new ArrayList<>();
        LambdaQueryWrapper<JobGradeType> jobGradeTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        jobGradeTypeLambdaQueryWrapper.orderByAsc(JobGradeType::getOrderNo);
        List<JobGradeType> jobGradeTypes = jobGradeTypeService.list(jobGradeTypeLambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(jobGradeTypes)){
            jobGradeTypes.forEach(jobGradeType -> {
                OrgTreeVo orgTreeVo = new OrgTreeVo();
                orgTreeVo.setId(jobGradeType.getId());
                orgTreeVo.setCode(jobGradeType.getCode());
                orgTreeVo.setName(jobGradeType.getName());
                orgTreeVo.setSourceType("1");
                orgTreeVos.add(orgTreeVo);
            });
        }
        Map<Long, JobGradeType> jobGradeTypeMap = jobGradeTypes.stream().collect(Collectors.toMap(JobGradeType::getId, jobGradeType -> jobGradeType));
        LambdaQueryWrapper<JobGrade> jobGradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        jobGradeLambdaQueryWrapper.orderByAsc(JobGrade::getOrderNo);
        List<JobGrade> jobGrades = this.list(jobGradeLambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(jobGrades)){
            jobGrades.forEach(jobGrade -> {
                OrgTreeVo orgTreeVo = new OrgTreeVo();
                orgTreeVo.setId(jobGrade.getId());
                JobGradeType jobGradeType = jobGradeTypeMap.get(jobGrade.getTypeId());
                if (jobGradeType != null) {
                    orgTreeVo.setPid(jobGradeType.getId());
                }
                orgTreeVo.setCode(jobGrade.getCode());
                orgTreeVo.setName(jobGrade.getName());
                orgTreeVo.setSourceType("2");
                orgTreeVos.add(orgTreeVo);
            });
        }
        return orgTreeVos;
    }


    /**
      * 分页查询JobGrade
      * @param page
      * @param jobGradeDTO 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, JobGradeDTO jobGradeDTO) {
        JobGrade jobGrade = new JobGrade();
        BeanCopyUtil.copyProperties(jobGradeDTO,jobGrade);
        IPage<JobGrade> selectPage = baseMapper.selectPage(page, Wrappers.query(jobGrade));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param jobGrade DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.JOB_GRADE_KEY, key = "#jobGrade.id")
    public Boolean saveJobGrade(JobGrade jobGrade) {

        return this.save(jobGrade);
    }


    /**
     * 通过ID查询JobGrade
     * @param id jobGradeID
     * @return JobGradeVO
     */
    @Override
    @Cacheable(value = CacheConstants.JOB_GRADE_KEY, key = "#id")
    public   JobGrade selectJobGradeById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除JobGrade
     * @param id jobGradeID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.JOB_GRADE_KEY, key = "#id")
    public Boolean deleteJobGradeById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#jobGrade.id")
    public Boolean updateJobGrade(JobGrade jobGrade) {
        return this.updateById(jobGrade);
    }


    @Override
    public List<JobGrade> getJobGrades(JobGrade jobGrade) {
        LambdaQueryWrapper<JobGrade> jobGradeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(jobGrade.getKeyword())){
            jobGradeLambdaQueryWrapper.like(JobGrade::getCode, jobGrade.getKeyword()).or()
                    .like(JobGrade::getName, jobGrade.getKeyword());
        }
        if (jobGrade.getTypeId()!=null && jobGrade.getTypeId()!= 0L){
            jobGradeLambdaQueryWrapper.eq(JobGrade::getTypeId, jobGrade.getTypeId());
        }
        if (StringUtils.isNotBlank(jobGrade.getTypeCode())){
            jobGradeLambdaQueryWrapper.eq(JobGrade::getTypeCode, jobGrade.getTypeCode());
        }
        jobGradeLambdaQueryWrapper.orderByAsc(JobGrade::getOrderNo);
        List<JobGrade> list = this.list(jobGradeLambdaQueryWrapper);
        return list;
    }
}
