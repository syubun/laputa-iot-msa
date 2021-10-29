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
import com.laputa.iot.org.api.dto.RoleDTO;
import com.laputa.iot.org.api.entity.Role;
import com.laputa.iot.org.mapper.RoleMapper;
import com.laputa.iot.org.service.RoleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 公司角色
 *
 * @author Sommer
 * @date 2021-09-29 22:08:34
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {




    /**
      * 分页查询Role
      * @param page
      * @param roleDTO 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, RoleDTO roleDTO) {
        Role role = new Role();
        BeanCopyUtil.copyProperties(roleDTO, role);
        IPage<Role> selectPage = baseMapper.selectPage(page, Wrappers.query(role));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param role DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.ROLE_KEY, key = "#role.id")
    public Boolean saveRole(Role role) {

        return this.save(role);
    }


    /**
     * 通过ID查询Role
     * @param id roleID
     * @return RoleVO
     */
    @Override
    @Cacheable(value = CacheConstants.ROLE_KEY, key = "#id")
    public Role selectRoleById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除Role
     * @param id roleID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.ROLE_KEY, key = "#id")
    public Boolean deleteRoleById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#role.id")
    public Boolean updateRole(Role role) {
        return this.updateById(role);
    }


    @Override
    public List<Role> getRolesByStaffId(Long staffId) {
        return baseMapper.getRolesByStaffId(staffId);
    }

    @Override
    public IPage<Role> getPageModelByQuery(Page page, Role role, Long staffId) {
        role.setStaffId(staffId);
       IPage<Role> rolePage =  baseMapper.getPagerModel(page, role);
        return rolePage;
    }
}
