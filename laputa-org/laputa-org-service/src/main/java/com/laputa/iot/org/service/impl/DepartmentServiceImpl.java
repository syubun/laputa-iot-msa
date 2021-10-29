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
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.api.dto.DepartmentDTO;
import com.laputa.iot.org.api.vo.OrgTreeVo;
import com.laputa.iot.org.mapper.DepartmentMapper;
import com.laputa.iot.org.service.CompanyService;
import com.laputa.iot.org.service.DepartmentService;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 部门表
 *
 * @author Sommer.jiang
 * @date 2021-10-03 10:45:58
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {


    @Autowired
    CompanyService companyService;


    /**
      * 分页查询Department
      * @param page
      * @param departmentDTO 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, DepartmentDTO departmentDTO) {
        Department department = new Department();
        BeanCopyUtil .copyProperties(departmentDTO,department);
        IPage<Department> selectPage = baseMapper.selectPage(page, Wrappers.query(department));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param department DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.DEPARTMENT_KEY, key = "#department.id")
    public Boolean saveDepartment(Department department) {

        return this.save(department);
    }


    /**
     * 通过ID查询Department
     * @param id departmentID
     * @return DepartmentVO
     */
    @Override
    @Cacheable(value = CacheConstants.DEPARTMENT_KEY, key = "#id")
    public   Department selectDepartmentById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除Department
     * @param id departmentID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.DEPARTMENT_KEY, key = "#id")
    public Boolean deleteDepartmentById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#department.id")
    public Boolean updateDepartment(Department department) {
        return this.updateById(department);
    }


    @Override
    public List<Department> getAll(Department department) {
        return baseMapper.getDepartments(department);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#department.id")
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public R<Boolean> saveOrUpdateDepartment(Department department) {
        return R.ok(this.saveOrUpdate(department));
    }


    @Override
    public List<OrgTreeVo> getOrgTree() {
        List<OrgTreeVo> orgTreeVos = companyService.getCompanyTree();
        Map<Long, OrgTreeVo> companyMap = orgTreeVos.stream().collect(Collectors.toMap(OrgTreeVo::getId, orgTreeVo -> orgTreeVo));
        List<Department> departments = baseMapper.getDepartments(new Department());
        if (CollectionUtils.isNotEmpty(departments)){
            departments.forEach(department -> {
                OrgTreeVo orgTreeVo = new OrgTreeVo(department.getId(), department.getPid(), department.getName(), department.getName(), OrgTreeVo.DEPT_TYPE);
                if (department.getPid().equals(CommonConstants.MENU_TREE_ROOT_ID)){
                    orgTreeVo.setPid(department.getCompanyId());
                }
                orgTreeVo.setLeaderCode(department.getLeaderCode());
                orgTreeVo.setLeaderName(department.getLeaderName());
                Long companyId = department.getCompanyId();
                OrgTreeVo company = companyMap.get(companyId);
                orgTreeVo.setCompanyId(companyId);
                orgTreeVo.setCompanyName(company.getName());
                orgTreeVos.add(orgTreeVo);
            });
        }
        return orgTreeVos;
    }


}
