/*
 *
 *      Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the www.laputa-iot.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: SommerJiang (sommer_jiang@163.com)
 *
 */

package com.laputa.iot.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laputa.iot.common.core.exception.BizException;
import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.upms.entity.PrivilegeAcl;
import com.laputa.iot.upms.entity.PrivilegeValue;
import com.laputa.iot.upms.entity.SysRoleMenu;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.upms.mapper.SysRoleMenuMapper;
import com.laputa.iot.upms.service.IAclService;
import com.laputa.iot.upms.service.SysMenuService;
import com.laputa.iot.upms.mapper.SysMenuMapper;
import com.laputa.iot.upms.service.SysRoleService;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.constant.enums.MenuTypeEnum;
import com.laputa.iot.common.core.base.dto.R;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 *
 * @author sommer.jiang
 * @since 2017-10-29
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final IAclService privilegeAclService;

    private final SysRoleService roleService;

    private final SysRoleMenuMapper roleMenuMapper;

    private final CacheManager cacheManager;

    //???????????????????????????????????????SpEL??????????????????
    public static final String KEY_CERT_TYPE_CODE_PREFIX = "'ec_cert_type:cert_type_code:'";


    @Override
    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId", unless = "#result.isEmpty()")
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        List<SysMenu> sysMenus = baseMapper.listMenusByRoleId(roleId);
        return sysMenus;
    }

    @Override
    public List<SysMenu> findMenuWithoutCatByRoleId(Long roleId) {
        List<SysMenu> sysMenus = baseMapper.listMenusNoCatByRoleId(roleId);
        return sysMenus;
    }


    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value ={CacheConstants.MENU_DETAILS, CacheConstants.MENU_ALL}, allEntries = true)
    public Boolean addMenu(SysMenu menu) {
        int result = baseMapper.insert(menu);
        if (result == 0) {
            throw new BizException("??????????????????");

        }

        LambdaQueryWrapper<SysMenu> privilegeAclLambdaQueryWrapper = new LambdaQueryWrapper<>(new SysMenu());
//        menu.setAlias(menu.getAlias());
        menu = baseMapper.selectOne(privilegeAclLambdaQueryWrapper.eq(SysMenu::getTag, menu.getTag()));
        if(menu == null){
            throw new BizException("????????????????????????");
        }
        PrivilegeAcl privilegeAcl = privilegeAclService.initPrivilege(menu);
        if (privilegeAcl == null) {
            throw new BizException("????????????????????????");
        }
        cacheManager.getCache(CacheConstants.MENU_ALL).clear();


        return Boolean.TRUE;
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {CacheConstants.MENU_DETAILS, CacheConstants.MENU_ALL}, allEntries = true)
    public R removeMenuById(Long id) {
        // ???????????????????????????????????????
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));
        if (CollUtil.isNotEmpty(menuList)) {
            return R.failed("??????????????????????????????");
        }

        roleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
//        cacheManager.getCache(CacheConstants.MENU_ALL).clear();
        // ?????????????????????????????????
        return R.ok(this.removeById(id));
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {CacheConstants.MENU_DETAILS, CacheConstants.MENU_ALL}, allEntries = true)
    public Boolean updateMenuById(SysMenu sysMenu) {
//		LbqWrapper lbqWrapper = new LbqWrapper(sysMenu);
//		LbqWrapper<SysMenu> query = Wraps.lbQ(sysMenu);
//        cacheManager.getCache(CacheConstants.MENU_ALL).clear();
        return this.updateById(sysMenu);
    }

    /**
     * ??????????????? 1. ???????????????????????????????????? 2. ?????????????????????parentId ?????? 2.1 ???????????????????????????ID -1
     *
     * @param lazy     ??????????????????
     * @param parentId ?????????ID
     * @return
     */
    @Override
    public List<Tree<Long>> treeMenu(boolean lazy, Long parentId) {
        if (!lazy) {
            List<TreeNode<Long>> collect = baseMapper
                    .selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort)).stream()
                    .map(getNodeFunction()).collect(Collectors.toList());

            return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
        }

        Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;

        List<TreeNode<Long>> collect = baseMapper
                .selectList(
                        Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent).orderByAsc(SysMenu::getSort))
                .stream().map(getNodeFunction()).collect(Collectors.toList());

        return TreeUtil.build(collect, parent);
    }

    /**
     * ??????????????? 1. ???????????????????????????????????? 2. ?????????????????????parentId ?????? 2.1 ???????????????????????????ID -1
     *
     * @return
     */
    @Override
    public List<Tree<Long>> allMenuTree() {

        List<TreeNode<Long>> collect = baseMapper
                .selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort)).stream()
                .map(getEasyNodeFunction()).collect(Collectors.toList());

        return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);


    }

    /**
     * ????????????
     *
     * @param all      ????????????
     * @param type     ??????
     * @param parentId ?????????ID
     * @return
     */
    @Override
    public List<Tree<Long>> filterMenu(Set<SysMenu> all, String type, Long parentId) {
        List<TreeNode<Long>> collect = all.stream().filter(menuTypePredicate(type)).map(getNodeFunction())
                .collect(Collectors.toList());

        Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.build(collect, parent);
    }

    @Override
    public List<SysMenu> getParentList() {
        // ???????????????????????????????????????
        List<SysMenu> menuList = baseMapper
                .selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort).eq(SysMenu::getType, 0));
//		SysMenu sysMenu = SysMenu.builder().id(CommonConstants.MENU_TREE_ROOT_ID).name("Parent").build();
//		menuList.add(sysMenu);
//		List<TreeNode<Long>> collect = menuList.stream()
//				.map(getNodeFunction()).collect(Collectors.toList());
//		return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
        return menuList;
    }

    @NotNull
    private Function<SysMenu, TreeNode<Long>> getEasyNodeFunction() {
        return menu -> {
            TreeNode<Long> node = new TreeNode<>();
            node.setId(menu.getId());
            node.setName(menu.getName());
            node.setParentId(menu.getParentId());
            node.setWeight(menu.getSort());
            // ????????????
            Map<String, Object> extra = new HashMap<>();

            extra.put("orderNo", menu.getSort());
            extra.put("title", menu.getTitle());
            extra.put("ignoreAuth", menu.getIgnoreAuth());
//			meta.put("roles",menu.getRoles());
            extra.put("ignoreKeepAlive", menu.getIgnoreKeepAlive());
            extra.put("affix", menu.getAffix());
            extra.put("icon", menu.getIcon());
            extra.put("frameSrc", menu.getFrameSrc());
            extra.put("transitionName", menu.getTransitionName());
            extra.put("hideBreadcrumb", menu.getHideBreadcrumb());
            extra.put("hideChildrenInMenu", menu.getHideChildrenInMenu());
            extra.put("carryParam", menu.getCarryParam());
            if (menu.getSingle()) {
                extra.put("single", menu.getSingle());
            }

            extra.put("currentActiveMenu", menu.getCurrentActiveMenu());
            extra.put("hideTab", menu.getHideTab());
            extra.put("hideMenu", menu.getHideMenu());
            extra.put("showMenu", menu.getShowMenu());
            extra.put("isLink", menu.getIsLink());
            extra.put("ignoreRoute", menu.getIgnoreRoute());
            extra.put("hidePathForChildren", menu.getHideChildrenInPath());
            extra.put("path", menu.getPath());
            extra.put("component", menu.getComponent());
            extra.put("permission", menu.getPermission());
            extra.put("redirect", menu.getRedirect());
            extra.put("sort", menu.getSort());
            extra.put("keepAlive", menu.getIgnoreKeepAlive());
            node.setExtra(extra);

            return node;
        };
    }


    @NotNull
    private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
        return menu -> {
            TreeNode<Long> node = new TreeNode<>();
            node.setId(menu.getId());
            node.setName(menu.getName());
            node.setParentId(menu.getParentId());
            node.setWeight(menu.getSort());
            // ????????????
            Map<String, Object> extra = new HashMap<>();
            Map<String, Object> meta = new HashMap<>();
            extra.put("type", menu.getType());
            meta.put("orderNo", menu.getSort());
            meta.put("title", menu.getTitle());
            meta.put("ignoreAuth", menu.getIgnoreAuth());
//			meta.put("roles",menu.getRoles());
            meta.put("ignoreKeepAlive", menu.getIgnoreKeepAlive());
            meta.put("affix", menu.getAffix());
            meta.put("icon", menu.getIcon());
            meta.put("frameSrc", menu.getFrameSrc());
            meta.put("transitionName", menu.getTransitionName());
            meta.put("hideBreadcrumb", menu.getHideBreadcrumb());
            meta.put("hideChildrenInMenu", menu.getHideChildrenInMenu());
            meta.put("carryParam", menu.getCarryParam());
            meta.put("currentActiveMenu", menu.getCurrentActiveMenu());
            meta.put("hideTab", menu.getHideTab());
            meta.put("hideMenu", menu.getHideMenu());
            meta.put("showMenu", menu.getShowMenu());
            meta.put("isLink", menu.getIsLink());
            meta.put("ignoreRoute", menu.getIgnoreRoute());
            meta.put("hidePathForChildren", menu.getHideChildrenInPath());
            if (menu.getSingle()) {
                meta.put("single", menu.getSingle());
            }


            extra.put("path", menu.getPath());
            extra.put("component", menu.getComponent());
            extra.put("permission", menu.getPermission());
            extra.put("redirect", menu.getRedirect());
            extra.put("sort", menu.getSort());
            extra.put("keepAlive", menu.getIgnoreKeepAlive());
            extra.put("meta", meta);
            node.setExtra(extra);

            return node;
        };
    }

    /**
     * menu ????????????
     *
     * @param type ??????
     * @return Predicate
     */
    private Predicate<SysMenu> menuTypePredicate(String type) {
        return vo -> {
            if (MenuTypeEnum.MENU_ROUTER.getDescription().equals(type)) {
                return MenuTypeEnum.MENU_ROUTER.getType().equals(vo.getType());
            }
            // ???????????? ?????? + ??????
            return !MenuTypeEnum.BUTTON.getType().equals(vo.getType());
        };
    }


    @Override
    @Cacheable(value = CacheConstants.MENU_ALL, unless = "#result.isEmpty()")
    public List<SysMenu> getAllMenu() {

        LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper = new LambdaQueryWrapper<>();

        sysMenuLambdaQueryWrapper.eq(SysMenu::getDeleted, 0).orderByAsc(SysMenu::getSort).le(SysMenu::getType, 1);
        List<SysMenu> sysMenus = this.list(sysMenuLambdaQueryWrapper);


        return sysMenus;
    }


    @Override
    public List<SysMenu> findAclsByRoleId(Long roleId) {
        List<SysMenu> sysMenus = baseMapper.listMenusByRoleId(roleId);
        SysRole role = roleService.getById(roleId);
        for (SysMenu sysMenu : sysMenus) {
            PrivilegeAcl privilegeAcl = privilegeAclService.getAclByRoleMenu(sysMenu.getId(), roleId);
//            if (privilegeAcl != null) {
//                privilegeAcl = PrivilegeAcl.builder()
//                        .menuId(sysMenu.getId())
//                        .menuSn(sysMenu.getAlias())
//                        .type(1)
//                        .aclState(new BigInteger(String.valueOf(0)))
//                        .roleId(role.getId())
//                        .roleSn(role.getCode())
//                        .build();
//                privilegeAcl = privilegeAclService.saveOne(privilegeAcl);
//
//            }

            if(privilegeAcl!=null){
                privilegeAcl = privilegeAclService.getPrivilegeAcls(privilegeAcl);
                sysMenu.setPvs(privilegeAcl.getPvs());
            }


        }
        return  sysMenus;
    }


    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#id", unless = "#result.isEmpty()")
    public SysMenu getMenuById(Long id) {
        SysMenu menu = getById(id);
        return menu;
    }


    private void setPermission(SysMenu sysMenu) {
        if (sysMenu.getPvs() != null && sysMenu.getPvs().size() > 0) {
            List<String> pm = new ArrayList<String>();
            for (PrivilegeValue pv : sysMenu.getPvs()) {
                pm.add(sysMenu.getAlias() + ":" + pv.getOperation());
            }
            String pms = String.join(",", pm);
            sysMenu.setPermission(pms);
        }


    }


}
