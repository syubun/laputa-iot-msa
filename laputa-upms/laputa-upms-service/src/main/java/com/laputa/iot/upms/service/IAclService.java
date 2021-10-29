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

package com.laputa.iot.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laputa.iot.upms.api.dto.AclDTO;
import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.upms.entity.PrivilegeAcl;
import com.laputa.iot.upms.entity.PrivilegeValue;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.common.core.base.dto.R;

import java.util.List;

/**
 * 模块权限表
 *
 * @author Sommer
 * @date 2021-09-08 12:48:31
 */
public interface IAclService extends IService<PrivilegeAcl> {
    /**
     * 得到菜单的权限值
     * @param menuId
     * @return
     */
    PrivilegeAcl getAclByMenu(Long menuId);
    /**
     * 根据角色ID跟菜单id得到权限值
     *
     * @param menuId
     * @param roleId
     * @return
     */
    PrivilegeAcl getAclByRoleMenu(Long menuId, Long roleId);

    /**
     * 保存并得到数据库中的值
     * @param privilegeAcl
     * @return
     */
    PrivilegeAcl saveOne(PrivilegeAcl privilegeAcl);

    PrivilegeAcl getPrivilegeAcls(PrivilegeAcl privilegeAcl);



    /**
     * 根据菜单初始化
      * @param sysMenu
     * @return
     */
    PrivilegeAcl initPrivilege(SysMenu sysMenu);
    /**
     * 根据菜单跟权限初始化
     * @param sysMenu
     * @return
     */
    PrivilegeAcl initPrivilegeByRole(SysMenu sysMenu, SysRole sysRole);

    R getPermCode();

    R addPriVal(List<Integer> positions, Long menuId);

    List<PrivilegeAcl> getAclByRoleId(Long roleId);

    R updatePriVal(AclDTO menu);


}
