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
import com.laputa.iot.org.api.entity.Department;
import com.laputa.iot.org.api.dto.DepartmentDTO;
import com.laputa.iot.org.api.vo.OrgTreeVo;

import java.util.List;

/**
 * 部门表
 *
 * @author Sommer.jiang
 * @date 2021-10-03 10:45:58
 */
public interface DepartmentService extends IService<Department> {



    /**
     * 分页查询Department
     * @param page 分页对象
     * @param departmentDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, DepartmentDTO departmentDTO);

    /**
     * 删除Department
     * @param id
     * @return boolean
     */
    Boolean deleteDepartmentById(Long id);

    /**
     * 更新指定Department
     * @param department Department信息
     * @return
     */
    Boolean updateDepartment(Department department);

    /**
     * 通过ID查询Department
     * @param id departmentID
     * @return DepartmentVO
     */
    Department selectDepartmentById(Long id);



    /**
     * 保存Department
     * @param department 对象
     * @return success/fail
     */
    Boolean saveDepartment(Department department);


    List<Department> getAll(Department department);

    R<Boolean> saveOrUpdateDepartment(Department department);

    /**
     * 获取组织树
     * @return
     */
    List<OrgTreeVo>  getOrgTree();
}
