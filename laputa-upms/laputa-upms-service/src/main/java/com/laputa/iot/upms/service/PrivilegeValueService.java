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
import com.laputa.iot.upms.entity.PrivilegeValue;

import java.util.List;

/**
 * 权限键值服务接口
 *
 * @author Sommer
 * @date 2021-09-08 12:57:42
 */
public interface PrivilegeValueService extends IService<PrivilegeValue> {
    /**
     * 得到所有的权限值
     * @return List<PrivilegeValue>
     */
    List<PrivilegeValue> getAllPrivilegeValue();

    /**
     * 更新或者保存权限值
     * @param privilegeValue
     * @return
     */
    boolean saveOrUpdate(PrivilegeValue privilegeValue);
}
