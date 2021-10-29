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
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.org.api.dto.StaffDTO;
import com.laputa.iot.upms.api.entity.SysUser;

import java.util.List;

/**
 * 员工
 *
 * @author Sommer
 * @date 2021-09-30 15:39:05
 */
public interface StaffService extends IService<Staff> {



    /**
     * 分页查询Staff
     * @param page 分页对象
     * @param staffDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, StaffDTO staffDTO);

    /**
     * 删除Staff
     * @param id
     * @return boolean
     */
    Boolean deleteStaffById(Long id);

    /**
     * 更新指定Staff
     * @param staff Staff信息
     * @return
     */
    Boolean updateStaff(Staff staff);

    /**
     * 通过ID查询Staff
     * @param id staffID
     * @return StaffVO
     */
    Staff selectStaffById(Long id);



    /**
     * 保存Staff
     * @param staff DTO 对象
     * @return success/fail
     */
    Boolean saveStaff(Staff staff);


    R getPagerModelByWrapper(Page page, Staff staff, boolean showRoles);

    /**
     * 添加或者修改人员信息
     *
     * @param staff  人员
     */
    boolean saveOrUpdateStaff(Staff staff);
    /**
     * 通过工号获取人员数据
     *
     * @param codes 工号列表
     * @return
     */
    List<Staff> getStaffsByCodes(List<String> codes);

    /**
     * 查询上级部门的用户信息
     * @param username 用户名
     * @return R
     */
    List<Staff> listAncestorUsers(String username);
}
