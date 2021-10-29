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
package com.laputa.iot.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.upms.entity.PrivilegeValue;
import com.laputa.iot.upms.mapper.PrivilegeValueMapper;
import com.laputa.iot.upms.service.PrivilegeValueService;
import com.laputa.iot.common.core.constant.CacheConstants;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限键值表
 *
 * @author Sommer
 * @date 2021-09-08 12:57:42
 */
@AllArgsConstructor
@Service
public class PrivilegeValueServiceImpl extends ServiceImpl<PrivilegeValueMapper, PrivilegeValue> implements PrivilegeValueService {
  final   private  CacheManager cacheManager;

    @Override
    @Cacheable(CacheConstants.PrivilegeValue_ALL)
    public List<PrivilegeValue> getAllPrivilegeValue() {
        return list();
    }

    @Override
//    @CacheEvict(value = CacheConstants.PrivilegeValue_ALL, allEntries = true)
    public boolean saveOrUpdate(PrivilegeValue privilegeValue) {
        cacheManager.getCache(CacheConstants.PrivilegeValue_ALL).clear();
      return  this.saveOrUpdate(privilegeValue);
    }
}
