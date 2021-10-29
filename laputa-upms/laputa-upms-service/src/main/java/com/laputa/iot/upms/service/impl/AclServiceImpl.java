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

import com.alibaba.nacos.common.utils.MapUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.upms.api.dto.AclDTO;
import com.laputa.iot.upms.entity.PrivilegeAcl;
import com.laputa.iot.upms.entity.PrivilegeValue;

import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.upms.mapper.PrivilegeAclMapper;
import com.laputa.iot.upms.service.IAclService;
import com.laputa.iot.upms.service.PrivilegeValueService;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.common.security.service.LaputaUser;
import com.laputa.iot.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 模块权限服务接口
 *
 * @author Sommer
 * @date 2021-09-08 12:48:31
 */
@Slf4j
@Service
@AllArgsConstructor
public class AclServiceImpl extends ServiceImpl<PrivilegeAclMapper, PrivilegeAcl> implements IAclService {

    private final PrivilegeValueService privilegeValueService;

    private final CacheManager cacheManager;


    @Override
    @Cacheable(value = CacheConstants.ACL_DETAILS, key = "#menuId")
    public PrivilegeAcl getAclByMenu(Long menuId) {
        LambdaQueryWrapper<PrivilegeAcl> privilegeAclLambdaQueryWrapper = new LambdaQueryWrapper<>();
        privilegeAclLambdaQueryWrapper.eq(PrivilegeAcl::getMenuId, menuId).eq(PrivilegeAcl::getType, 0);
        PrivilegeAcl privilegeAcl = baseMapper.selectOne(privilegeAclLambdaQueryWrapper);
        if (privilegeAcl != null) {
            if (privilegeAcl.getPvs() == null || privilegeAcl.getPvs().isEmpty()) {
                getPrivilegeAcls(privilegeAcl);
            }

        }
        return privilegeAcl;
    }


    @Override
    @Cacheable(value = CacheConstants.ACL_DETAILS, key = "#menuId+'-'+#roleId")
    public PrivilegeAcl getAclByRoleMenu(Long menuId, Long roleId) {
        LambdaQueryWrapper<PrivilegeAcl> privilegeAclLambdaQueryWrapper = new LambdaQueryWrapper<>();
        privilegeAclLambdaQueryWrapper.eq(PrivilegeAcl::getMenuId, menuId).eq(PrivilegeAcl::getType, 1).eq(PrivilegeAcl::getRoleId, roleId);
        PrivilegeAcl privilegeAcl = baseMapper.selectOne(privilegeAclLambdaQueryWrapper);
        if (privilegeAcl != null) {
            if (privilegeAcl.getPvs() == null || privilegeAcl.getPvs().isEmpty()) {
                getPrivilegeAcls(privilegeAcl);
            }

        }
        return privilegeAcl;
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.ACL_DETAILS, allEntries = true)
    public PrivilegeAcl initPrivilege(SysMenu sysMenu) {
        PrivilegeAcl privilegeAcl = getAclByMenu(sysMenu.getId());
        if (privilegeAcl == null) {
            privilegeAcl = PrivilegeAcl.builder()
                    .menuId(sysMenu.getId())
                    .menuSn(sysMenu.getTag())
                    .type(0)
                    .aclState(new BigInteger(String.valueOf(0)))
                    .build();
            int result = baseMapper.insert(privilegeAcl);
            if (result > 0) {
                LambdaQueryWrapper<PrivilegeAcl> privilegeAclLambdaQueryWrapper = new LambdaQueryWrapper<>(new PrivilegeAcl());
                privilegeAclLambdaQueryWrapper.eq(PrivilegeAcl::getMenuId, sysMenu.getId());
                privilegeAcl = baseMapper.selectOne(privilegeAclLambdaQueryWrapper);
                return privilegeAcl;
            } else {
                return privilegeAcl;
            }

        } else {
            return privilegeAcl;
        }

    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.ACL_DETAILS, allEntries = true)
    public PrivilegeAcl initPrivilegeByRole(SysMenu sysMenu, SysRole sysRole) {
        PrivilegeAcl privilegeAcl = getAclByRoleMenu(sysMenu.getId(), sysRole.getId());
        if (privilegeAcl == null) {
            privilegeAcl = PrivilegeAcl.builder()
                    .menuId(sysMenu.getId())
                    .menuSn(sysMenu.getAlias())
                    .type(1)
                    .roleSn(sysRole.getCode())
                    .roleId(sysRole.getId())
                    .aclState(new BigInteger(String.valueOf(0)))
                    .build();
            int result = baseMapper.insert(privilegeAcl);
            if (result > 0) {
                LambdaQueryWrapper<PrivilegeAcl> privilegeAclLambdaQueryWrapper = new LambdaQueryWrapper<>(privilegeAcl);
                privilegeAcl = baseMapper.selectOne(privilegeAclLambdaQueryWrapper);
                return privilegeAcl;
            } else {
                return privilegeAcl;
            }

        } else {
            return privilegeAcl;
        }

    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.ACL_DETAILS, allEntries = true)
    public PrivilegeAcl saveOne(PrivilegeAcl privilegeAcl) {
        int result = baseMapper.insert(privilegeAcl);
        if (result > 0) {
            LambdaQueryWrapper<PrivilegeAcl> privilegeAclLambdaQueryWrapper = new LambdaQueryWrapper<>(privilegeAcl);
            return baseMapper.selectOne(privilegeAclLambdaQueryWrapper);
        } else {
            return null;
        }


    }

    @Override
    @Cacheable(value = CacheConstants.ACL_DETAILS, key = "#acl.id")
    public PrivilegeAcl getPrivilegeAcls(PrivilegeAcl acl) {
        List<PrivilegeValue> spvs = privilegeValueService.getAllPrivilegeValue();
        List<PrivilegeValue> msvs = new ArrayList<>();
        List<String> sb = new ArrayList<>();
        for (PrivilegeValue spv : spvs) {
            int flag = acl.getPermission(spv.getPosition());
            if (flag == PrivilegeAcl.ACL_YES) {

                PrivilegeValue newPv = new PrivilegeValue();
                // 对象一定要克隆一下，否则就会出现问题
                BeanCopyUtil.copyProperties(spv, newPv);
                newPv.setPermissionName(acl.getMenuSn() + ":" + newPv.getOperation());
                msvs.add(newPv);
                sb.add(newPv.getPermissionName());
            }
        }
        acl.setPvs(msvs);
        acl.setPvsPermission(StringUtils.join(sb.toArray(), ","));
        if(!sb.isEmpty()){
            acl.setRemark(StringUtils.join(sb.toArray(), ","));
        }else {
            acl.setRemark("");
        }

        return acl;
    }

    @Cacheable(value = CacheConstants.ACL_VALUE_DETAILS, key = "#acl.aclState")
    public List<PrivilegeValue> getAclValues(PrivilegeAcl acl) {
        List<PrivilegeValue> spvs = privilegeValueService.getAllPrivilegeValue();
        List<PrivilegeValue> msvs = new ArrayList<>();

        for (PrivilegeValue spv : spvs) {
            int flag = acl.getPermission(spv.getPosition());
            if (flag == PrivilegeAcl.ACL_YES) {
                PrivilegeValue newPv = new PrivilegeValue();
                // 对象一定要克隆一下，否则就会出现问题
                BeanCopyUtil.copyProperties(spv, newPv);
                msvs.add(newPv);
            }
        }

        return msvs;
    }


    @Override
    public R getPermCode() {
        LaputaUser user = SecurityUtils.getUser();
        if (user == null) {
            return R.fail("用户不存在");
        }

        return R.ok(user.getAuthorities());
    }

    @Override
    public R addPriVal(List<Integer> positions, Long menuId) {
        PrivilegeAcl acl = getAclByMenu(menuId);
        if (acl != null) {
            updateAclValues(positions, acl);
            return R.ok();
        } else {
            return R.failed("没有对应的权限");
        }

    }

    @Override
    @CacheEvict(value = CacheConstants.ACL_DETAILS, key = "#menuId+'-'+#roleId")
    public R updatePriVal(AclDTO aclDTO) {
        List<Integer> positions = new ArrayList<>();
        if(aclDTO.getPvs()!=null&& aclDTO.getPvs().size()>0){
            positions = aclDTO.getPvs().stream().map(apv -> {
                return apv.getPosition();
            }).collect(Collectors.toList());
        }

        PrivilegeAcl acl = getAclByRoleMenu(aclDTO.getMenuId(), aclDTO.getRoleId());
        if (acl == null) {
            acl = PrivilegeAcl.builder()
                    .type(1)
                    .menuId(aclDTO.getMenuId())
                    .menuSn(aclDTO.getMenuSn())
                    .roleId(aclDTO.getRoleId())
                    .roleSn(aclDTO.getRoleSn())
                    .aclState(new BigInteger("0"))
                    .build();
            if(positions.size()>0){
                acl.setAclValueByPositions(positions);
            }

            boolean result = this.save(acl);
            if (result) {
                return R.ok(Boolean.TRUE);
            } else {
                return R.failed("保存角色的权限值失败!");
            }
        } else {

            updateAclValues(positions, acl);
            return R.ok(Boolean.TRUE);
        }

    }



    @Override
    @Cacheable(value = CacheConstants.ROLE_ACL, key = "#roleId")
    public List<PrivilegeAcl> getAclByRoleId(Long roleId) {
        LambdaQueryWrapper<PrivilegeAcl> aclLambdaQueryWrapper = new LambdaQueryWrapper<>();
        aclLambdaQueryWrapper.eq(PrivilegeAcl::getRoleId, roleId).eq(PrivilegeAcl::getType, 1);
        List<PrivilegeAcl> roleAcls = baseMapper.selectList(aclLambdaQueryWrapper);
        roleAcls.forEach(acl -> {
            getPrivilegeAcls(acl);
        });
        return roleAcls;
    }

    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.ACL_DETAILS, key = "#acl.id")
    public void updateAclValues(List<Integer> positions, PrivilegeAcl acl) {

        acl.setAclValueByPositions(positions);
        acl = getPrivilegeAcls(acl);
        updateById(acl);
        cacheManager.getCache(CacheConstants.ACL_DETAILS).clear();

    }


    public Set<PrivilegeAcl> getAclsByUserId(String userId, List<SysRole> roles) {
        Set<PrivilegeAcl> acls = new HashSet<>();
        Map<String, PrivilegeAcl> moduleAcls = new HashMap<>();
        if (CollectionUtils.isNotEmpty(roles)) {
            List<Long> roleIds = new ArrayList<>();
            for (SysRole sysRole : roles) {
                roleIds.add(sysRole.getId());
            }
            List<PrivilegeAcl> roleAcls = baseMapper.getAclsBySysRoleIds(roleIds);
            if (CollectionUtils.isNotEmpty(roleAcls)) {
                for (PrivilegeAcl acl : roleAcls) {
                    Long moduleId = acl.getMenuId();
                    if (moduleAcls.containsKey(acl.getMenuId())) {
                        PrivilegeAcl mAcl = moduleAcls.get(moduleId);
                        BigInteger mAclState = mAcl.getAclState() == null ? new BigInteger("0") : mAcl.getAclState();
                        BigInteger aAclState = acl.getAclState() == null ? new BigInteger("0") : acl.getAclState();
                        mAcl.setAclState(mAclState.or(aAclState));
                        moduleAcls.put(String.valueOf(acl.getMenuId()), mAcl);
                    } else {
                        moduleAcls.put(String.valueOf(acl.getMenuId()), acl);
                    }
                }
            }
            // 转化成set
            if (MapUtils.isNotEmpty(moduleAcls)) {
                moduleAcls.forEach((k, v) -> acls.add(v));
            }
        }
        return acls;
    }
}
