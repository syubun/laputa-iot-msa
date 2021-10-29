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
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.org.api.entity.Company;
import com.laputa.iot.org.api.dto.CompanyDTO;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.api.vo.OrgTreeVo;
import com.laputa.iot.org.mapper.CompanyMapper;
import com.laputa.iot.org.service.CompanyService;
import com.laputa.iot.org.service.DepartmentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 公司表
 *
 * @author Sommer
 * @date 2021-09-30 14:10:33
 */
@Service
@Slf4j
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

@Autowired
    DepartmentService departmentService;


    /**
      * 分页查询Company
      * @param page
      * @param companyDTO 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, CompanyDTO companyDTO) {
        Company company = new Company();
        BeanCopyUtil.copyProperties(companyDTO,company);
        IPage<Company> selectPage = baseMapper.selectPage(page, Wrappers.query(company));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param company DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.COMPANY_KEY, key = "#company.id")
    public Boolean saveCompany(Company company) {

        return this.save(company);
    }

    @Override
    public IPage getCompanysByPage(Page page, Company company) {
        log.info(company.getKeyword());
        //参数一是当前页，参数二是每页个数
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(company.getKeyword())){
            companyLambdaQueryWrapper.like(Company::getName, company.getKeyword()).or().like(Company::getCode, company.getCode());
        }
        if (company.getStatus() != null){
            companyLambdaQueryWrapper.eq(Company::getStatus, company.getStatus());
        }
        companyLambdaQueryWrapper.orderByAsc(Company::getOrderNo);
        return this.page(page, companyLambdaQueryWrapper);
    }


    /**
     * 通过ID查询Company
     * @param id companyID
     * @return CompanyVO
     */
    @Override
    @Cacheable(value = CacheConstants.COMPANY_KEY, key = "#id")
    public   Company selectCompanyById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除Company
     * @param id companyID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.COMPANY_KEY, key = "#id")
    public Boolean deleteCompanyById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.COMPANY_KEY, key = "#company.id")
    public Boolean updateCompany(Company company) {
        return this.updateById(company);
    }


    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.COMPANY_KEY, allEntries = true)
    public R deleteByIds(List<Long> ids) {
            LambdaQueryWrapper<Department> departmentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            departmentLambdaQueryWrapper.in(Department::getCompanyId, ids)
                    .eq(Department::getDeleted, CommonConstants.DEL_FLAG_0);
            int count = departmentService.count(departmentLambdaQueryWrapper);
            if (count > 0){
               return R.fail("请先删除相关部门！");
            } else {
                LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<>();
                companyLambdaQueryWrapper.eq(Company::getDeleted, CommonConstants.DEL_FLAG_0)
                        .eq(Company::getPid, ids.get(0));
                int companyCount = this.count(companyLambdaQueryWrapper);
                if (companyCount > 0){
                    return R.failed( "该公司还有子公司，请确认！");
                } else {
                    LambdaUpdateWrapper<Company> companyLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                    companyLambdaUpdateWrapper.set(Company::getDeleted, CommonConstants.DEL_FLAG_1)
                            .in(Company::getId, ids);
                    this.update(companyLambdaUpdateWrapper);
                }
            }
            return R.ok();

    }


    @Override
    public List<OrgTreeVo> getCompanyTree() {
        List<OrgTreeVo> orgTreeVos = new ArrayList<>();
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        companyLambdaQueryWrapper.eq(Company::getStatus, 1).eq(Company::getDeleted, CommonConstants.DEL_FLAG_0);
        List<Company> companies = this.list(companyLambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(companies)){
            companies.forEach(company -> {
                OrgTreeVo orgTreeVo = new OrgTreeVo(company.getId(), company.getPid(), company.getName(), company.getShortName(), OrgTreeVo.COMPANY_TYPE);
                orgTreeVos.add(orgTreeVo);
            });
        }
        return orgTreeVos;
    }

}
