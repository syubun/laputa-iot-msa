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

import com.laputa.iot.org.api.entity.Role;
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.org.api.entity.StaffRole;

import com.laputa.iot.org.api.vo.RoleStaffVo;
import com.laputa.iot.org.mapper.StaffRoleMapper;
import com.laputa.iot.org.service.RoleService;
import com.laputa.iot.org.service.StaffRoleService;

import com.laputa.iot.org.service.StaffService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 员工角色
 *
 * @author Sommer
 * @date 2021-10-10 13:16:18
 */
@Service
public class StaffRoleServiceImpl extends ServiceImpl<StaffRoleMapper, StaffRole> implements StaffRoleService {


    @Autowired
    RoleService roleService;
    @Autowired
    StaffService staffService;

    /**
     * 分页查询StaffRole
     *
     * @param page
     * @param staffRole 参数列表
     * @return
     */
    @Override
    public IPage queryPage(Page page, StaffRole staffRole) {
        IPage<StaffRole> selectPage = baseMapper.selectPage(page, Wrappers.query(staffRole));

        return selectPage;
    }


    /**
     * 通过ID查询StaffRole
     *
     * @param id staffRoleID
     * @return StaffRoleVO
     */
    @Override

    public StaffRole selectStaffRoleById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 保存用户信息
     *
     * @param staffRole DTO 对象
     * @return success/fail
     */
    @Override
    @Transactional(rollbackFor = Exception.class)

    public Boolean saveStaffRole(StaffRole staffRole) {

        return this.save(staffRole);
    }


    /**
     * 删除StaffRole
     *
     * @param id staffRoleID
     * @return Boolean
     */
    @Override

    public Boolean deleteStaffRoleById(Long id) {

        return this.removeById(id);
    }

    @Override

    public Boolean updateStaffRole(StaffRole staffRole) {
        return this.updateById(staffRole);
    }


    @Override
    public List<RoleStaffVo> getRoleStaffs(StaffRole staffRole) {
        List<StaffRole> staffRoles = baseMapper.getAllByQuery(staffRole);

        Role role = roleService.getById(staffRole.getRoleId());

        List<RoleStaffVo> roleStaffVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(staffRoles)) {
            List<String> codes = staffRoles.stream().map(e -> e.getStaffCode()).distinct().collect(Collectors.toList());

            // 通过用户工号得到用户的其他信息设置进去
            List<Staff> staffs = staffService.getStaffsByCodes(codes);
            if (CollectionUtils.isNotEmpty(staffs)) {
                Map<String, Staff> staffMap = staffs.stream().collect(Collectors.toMap(Staff::getCode, entity -> entity));
                staffRoles.stream().forEach(item -> {
                    Staff staff = staffMap.get(item.getStaffCode());
                    RoleStaffVo roleStaffVo = new RoleStaffVo();
                    roleStaffVo.setId(item.getId());
                    roleStaffVo.setStaffId(staff.getId());
                    roleStaffVo.setRoleId(staffRole.getRoleId());
                    roleStaffVo.setCode(staff.getCode());
                    roleStaffVo.setName(staff.getName());
                    roleStaffVo.setDeptId(staff.getDeptId());
                    roleStaffVo.setDeptName(staff.getDeptName());
                    roleStaffVo.setCompanyId(staff.getCompanyId());
                    roleStaffVo.setCompanyName(staff.getCompanyName());
                    roleStaffVo.setRoleName(role.getName());
                    roleStaffVos.add(roleStaffVo);
                });
            }

        }
        return roleStaffVos;
    }

    @Override
    public Boolean addStaffRolesByStaff(Long staffId, List<Role> roles) {
        List<StaffRole> prs = new ArrayList<>();
        Staff staff = staffService.getById(staffId);
        Date date = new Date();
        if (CollectionUtils.isNotEmpty(roles) && staff != null){
            roles.forEach(role -> {
                StaffRole pr = new StaffRole();
                pr.setStaffId(staffId);
                pr.setStaffCode(staff.getCode());
                pr.setRoleId(role.getId());
                prs.add(pr);
            });
           return this.saveBatch(prs);
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean addStaffRolesByRole(Long roleId, List<Staff> staffs) {
        if (roleId!=null && roleId!= 0L){
            LambdaQueryWrapper<StaffRole> staffRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
            staffRoleLambdaQueryWrapper.eq(StaffRole::getRoleId, roleId);
            this.remove(staffRoleLambdaQueryWrapper);
        }
        List<StaffRole> prs = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(staffs) && roleId!=null && roleId!= 0L){
            staffs.forEach(staff -> {
                StaffRole pr = new StaffRole();
                pr.setStaffId(staff.getId());
                pr.setStaffCode(staff.getCode());
                pr.setRoleId(roleId);
                prs.add(pr);
            });
          return  this.saveBatch(prs);
        }
        return Boolean.FALSE;
    }
}
