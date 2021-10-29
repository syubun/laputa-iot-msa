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
import com.laputa.iot.org.api.entity.JobGradeType;
import com.laputa.iot.org.api.dto.JobGradeTypeDTO;
import com.laputa.iot.org.mapper.JobGradeTypeMapper;
import com.laputa.iot.org.service.JobGradeTypeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;



/**
 * 职级分类
 *
 * @author Sommer
 * @date 2021-10-03 11:11:22
 */
@Service
public class JobGradeTypeServiceImpl extends ServiceImpl<JobGradeTypeMapper, JobGradeType> implements JobGradeTypeService {




    /**
      * 分页查询JobGradeType
      * @param page
      * @param jobGradeTypeDTO 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, JobGradeTypeDTO jobGradeTypeDTO) {
        JobGradeType jobGradeType = new JobGradeType();
        BeanCopyUtil.copyProperties(jobGradeTypeDTO,jobGradeType);
        IPage<JobGradeType> selectPage = baseMapper.selectPage(page, Wrappers.query(jobGradeType));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param jobGradeType DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.JOB_GRADE_TYPE_KEY, key = "#jobGradeType.id")
    public Boolean saveJobGradeType(JobGradeType jobGradeType) {

        return this.save(jobGradeType);
    }


    /**
     * 通过ID查询JobGradeType
     * @param id jobGradeTypeID
     * @return JobGradeTypeVO
     */
    @Override
    @Cacheable(value = CacheConstants.JOB_GRADE_TYPE_KEY, key = "#id")
    public   JobGradeType selectJobGradeTypeById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除JobGradeType
     * @param id jobGradeTypeID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.JOB_GRADE_TYPE_KEY, key = "#id")
    public Boolean deleteJobGradeTypeById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#jobGradeType.id")
    public Boolean updateJobGradeType(JobGradeType jobGradeType) {
        return this.updateById(jobGradeType);
    }





}
