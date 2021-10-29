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
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.org.api.entity.Company;
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.api.entity.Role;
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.org.api.dto.StaffDTO;
import com.laputa.iot.org.mapper.StaffMapper;
import com.laputa.iot.org.service.CompanyService;
import com.laputa.iot.org.service.DepartmentService;
import com.laputa.iot.org.service.RoleService;
import com.laputa.iot.org.service.StaffService;
import com.laputa.iot.upms.api.entity.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 员工
 *
 * @author Sommer
 * @date 2021-09-30 15:39:05
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {



    @Autowired
    RoleService roleService;

    @Lazy
    @Autowired
    private CompanyService companyService;
    @Lazy
    @Autowired
    private DepartmentService departmentService;

    /**
      * 分页查询Staff
      * @param page
      * @param staffDTO 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, StaffDTO staffDTO) {
        Staff staff = new Staff();
        BeanCopyUtil.copyProperties(staffDTO,staff);
        IPage<Staff> selectPage = baseMapper.selectPage(page, Wrappers.query(staff));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param staff DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.STAFF_KEY, key = "#staff.id")
    public Boolean saveStaff(Staff staff) {

        return this.save(staff);
    }

    @Override
    public R getPagerModelByWrapper(Page page, Staff staff, boolean showRoles) {
          IPage<Staff> queryPage = baseMapper.getPagerModel(page, staff);
        List<Staff> list = queryPage.getRecords();
        if (showRoles && CollectionUtils.isNotEmpty(list)){
            list.forEach(staff1 -> {
                List<Role> roles = roleService.getRolesByStaffId(staff1.getId());
                if (CollectionUtils.isNotEmpty(roles)){
                    staff1.setRoles(roles);
                }
            });
        }
        return R.ok(queryPage.setRecords(list));
    }



    @CacheEvict(value = CacheConstants.STAFF_KEY, key = "#staff.id")
    @Override
    public boolean saveOrUpdateStaff(Staff staff) {

        if (staff.getCompanyId() != 0L){
            Company company = companyService.getById(staff.getCompanyId());
            staff.setCompanyName(company.getName());
        }
        if (staff.getDeptId() != 0L){
            Department department = departmentService.getById(staff.getDeptId());
            staff.setDeptName(department.getName());
        }

           return    this.saveOrUpdate(staff);
    }

    /**
     * 通过ID查询Staff
     * @param id staffID
     * @return StaffVO
     */
    @Override
    @Cacheable(value = CacheConstants.STAFF_KEY, key = "#id")
    public   Staff selectStaffById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除Staff
     * @param id staffID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.STAFF_KEY, key = "#id")
    public Boolean deleteStaffById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#staff.id")
    public Boolean updateStaff(Staff staff) {
        return this.updateById(staff);
    }


    @Override
    public List<Staff> getStaffsByCodes(List<String> codes) {
        List<Staff> staffList = null;
        if(CollectionUtils.isNotEmpty(codes)) {
            LambdaQueryWrapper<Staff> personalQueryWrapper = new LambdaQueryWrapper<>();
            personalQueryWrapper.in(Staff::getCode, codes);
            staffList = this.list(personalQueryWrapper);
        }
        return staffList;
    }

    /**
     * 查询上级部门的用户信息
     * @param username 用户名
     * @return R
     */
    @Override
    public List<Staff> listAncestorUsers(String username) {
        //sommer 20210723
		Staff staff = this.getOne(Wrappers.<Staff>query().lambda().eq(Staff::getName, username));

		Department sysDept = departmentService.getById(staff.getDeptId());
		if (sysDept == null) {
			return null;
		}

		Long parentId = sysDept.getPid();
		return this.list(Wrappers.<Staff>query().lambda().eq(Staff::getDeptId, parentId));
        // sommer.jiang 2021.0723

    }

    /**
     * 获取当前用户的子部门信息
     * @return 子部门列表
     */
    private List<Integer> getChildDepts() {
        // sommer.jiang 2021.0723
//		Integer deptId = SecurityUtils.getUser().getDeptId();
//		// 获取当前部门的子部门
//		return sysDeptRelationService
//				.list(Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getAncestor, deptId)).stream()
//				.map(SysDeptRelation::getDescendant).collect(Collectors.toList());
        return null;
    }
}
