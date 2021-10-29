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
import com.laputa.iot.org.api.entity.Role;
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.org.api.entity.StaffRole;
import com.laputa.iot.org.api.vo.RoleStaffVo;

import java.util.List;


/**
 * 员工角色
 *
 * @author Sommer
 * @date 2021-10-10 13:16:18
 */
public interface StaffRoleService extends IService<StaffRole> {



    /**
     * 分页查询StaffRole
     * @param page 分页对象
     * @param staffRoleDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, StaffRole staffRoleDTO);

    /**
     * 删除StaffRole
     * @param id
     * @return boolean
     */
    Boolean deleteStaffRoleById(Long id);

    /**
     * 更新指定StaffRole
     * @param staffRole StaffRole信息
     * @return
     */
    Boolean updateStaffRole(StaffRole staffRole);

    /**
     * 通过ID查询StaffRole
     * @param id staffRoleID
     * @return StaffRoleVO
     */
    StaffRole selectStaffRoleById(Long id);



    /**
     * 保存StaffRole
     * @param staffRole DTO 对象
     * @return success/fail
     */
    Boolean saveStaffRole(StaffRole staffRole);

    /**
     * 得到角色的职员名
     * @param staffRole
     * @return
     * @throws Exception
     * @Description:
     */
    List<RoleStaffVo> getRoleStaffs(StaffRole staffRole);

    Boolean addStaffRolesByStaff(Long staffId, List<Role> roles);

    Boolean addStaffRolesByRole(Long roleId, List<Staff> staffs);
}
