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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.org.api.dto.RoleDTO;
import com.laputa.iot.org.api.entity.Role;

import java.util.List;

/**
 * 公司角色
 *
 * @author Sommer
 * @date 2021-09-29 22:08:34
 */
public interface RoleService extends IService<Role> {



    /**
     * 分页查询Role
     * @param page 分页对象
     * @param roleDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, RoleDTO roleDTO);

    /**
     * 删除Role
     * @param id
     * @return boolean
     */
    Boolean deleteRoleById(Long id);

    /**
     * 更新指定Role
     * @param role Role信息
     * @return
     */
    Boolean updateRole(Role role);

    /**
     * 通过ID查询Role
     * @param id roleID
     * @return RoleVO
     */
    Role selectRoleById(Long id);



    /**
     * 保存Role
     * @param role DTO 对象
     * @return success/fail
     */
    Boolean saveRole(Role role);


    /**
     * 通过人员id获取人员的角色列表
     *
     * @param personalId 人员id
     * @return
     */
    List<Role> getRolesByStaffId(Long personalId);

    IPage<Role> getPageModelByQuery(Page page, Role role, Long staffId);
}
