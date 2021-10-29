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

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.org.api.entity.JobGradeType;
import com.laputa.iot.org.api.dto.JobGradeTypeDTO;

/**
 * 职级分类
 *
 * @author Sommer
 * @date 2021-10-03 11:11:22
 */
public interface JobGradeTypeService extends IService<JobGradeType> {



    /**
     * 分页查询JobGradeType
     * @param page 分页对象
     * @param jobGradeTypeDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, JobGradeTypeDTO jobGradeTypeDTO);

    /**
     * 删除JobGradeType
     * @param id
     * @return boolean
     */
    Boolean deleteJobGradeTypeById(Long id);

    /**
     * 更新指定JobGradeType
     * @param jobGradeType JobGradeType信息
     * @return
     */
    Boolean updateJobGradeType(JobGradeType jobGradeType);

    /**
     * 通过ID查询JobGradeType
     * @param id jobGradeTypeID
     * @return JobGradeTypeVO
     */
    JobGradeType selectJobGradeTypeById(Long id);



    /**
     * 保存JobGradeType
     * @param jobGradeTypeDTO DTO 对象
     * @return success/fail
     */
    Boolean saveJobGradeType(JobGradeType jobGradeType);


}
