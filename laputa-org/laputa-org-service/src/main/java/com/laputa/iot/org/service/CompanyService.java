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
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.org.api.dto.CompanyDTO;
import com.laputa.iot.org.api.entity.Company;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laputa.iot.org.api.vo.OrgTreeVo;

import java.util.List;

/**
 * 公司表
 *
 * @author Sommer
 * @date 2021-09-30 14:10:33
 */
public interface CompanyService extends IService<Company> {



    /**
     * 分页查询Company
     * @param page 分页对象
     * @param companyDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, CompanyDTO companyDTO);

    /**
     * 删除Company
     * @param id
     * @return boolean
     */
    Boolean deleteCompanyById(Long id);

    /**
     * 更新指定Company
     * @param company Company信息
     * @return
     */
    Boolean updateCompany(Company company);

    /**
     * 通过ID查询Company
     * @param id companyID
     * @return CompanyVO
     */
    Company selectCompanyById(Long id);



    /**
     * 保存Company
     * @param company DTO 对象
     * @return success/fail
     */
    Boolean saveCompany(Company company);


    IPage getCompanysByPage(Page page, Company company);

    R deleteByIds(List<Long> ids);

    /**
     * 获取公司树
     *
     * @return
     */
    List<OrgTreeVo> getCompanyTree();
}
