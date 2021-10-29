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

import com.laputa.iot.org.api.dto.JobGradeDTO;
import com.laputa.iot.org.api.entity.JobGrade;
import com.laputa.iot.org.api.vo.OrgTreeVo;

import java.util.List;

/**
 * 工作等级表
 *
 * @author Sommer
 * @date 2021-10-03 11:35:01
 */
public interface JobGradeService extends IService<JobGrade> {



    /**
     * 分页查询JobGrade
     * @param page 分页对象
     * @param jobGradeDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, JobGradeDTO jobGradeDTO);

    /**
     * 删除JobGrade
     * @param id
     * @return boolean
     */
    Boolean deleteJobGradeById(Long id);

    /**
     * 更新指定JobGrade
     * @param jobGrade JobGrade信息
     * @return
     */
    Boolean updateJobGrade(JobGrade jobGrade);

    /**
     * 通过ID查询JobGrade
     * @param id jobGradeID
     * @return JobGradeVO
     */
    JobGrade selectJobGradeById(Long id);



    /**
     * 保存JobGrade
     * @param jobGrade DTO 对象
     * @return success/fail
     */
    Boolean saveJobGrade(JobGrade jobGrade);
    /**
     * 得到职级和职级分类组装的树
     * @return
     */
    List<OrgTreeVo> getJobGradeTree() ;


    List<JobGrade> getJobGrades(JobGrade jobGrade);
}
