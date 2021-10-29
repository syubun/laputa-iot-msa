package com.laputa.iot.upms.service.impl;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.org.api.client.StaffRemoteService;
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.upms.LaputaUpmsService;
import com.laputa.iot.upms.api.entity.SysUser;
import com.laputa.iot.upms.entity.PrivilegeAcl;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.upms.service.SysUserService;
import io.jsonwebtoken.lang.Assert;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = LaputaUpmsService.class)
class PrivilegeAclServiceImplTest {

    @Autowired
    AclServiceImpl privilegeAclService;
    @Autowired
    SysMenuServiceImpl sysMenuService;
    @Autowired
    SysRoleServiceImpl roleService;



    @Test
    @Ignore
    void getAclByRoleId() {

        List<SysMenu> allMenu = sysMenuService.getAllMenu();
//        acls.stream().map(acl->{
//            return PrivilegeAcl.builder()
//                    .menuId(acl.getMenuId())
//                    .menuSn(acl.getMenuSn())
//
//        }).collect(Collectors.toList());

        allMenu.forEach(menu->{
          PrivilegeAcl  acl =    privilegeAclService.getAclByMenu(menu.getId());
            System.out.println(acl.getPvsPermission());
//            if (acl.getPvs()!=null && acl.getPvs().size()>0) {
//                acl.getPvs().forEach(privilegeValue -> {
//                    System.out.println(privilegeValue.getPermissionName());
//                });
//            }
        });

    }



    @Test
    @Ignore
    void getMenuById() {
//        SysRole role = roleService.getById(886923984392683649l);
//        roleService.updateRoleMenus()
        List<SysMenu> allMenu = sysMenuService.getAllMenu();
        allMenu.stream().forEach(sysMenu -> {
            PrivilegeAcl privilegeAcl = privilegeAclService.initPrivilege(sysMenu);
            System.out.println(privilegeAcl);
            Assert.notNull(privilegeAcl);
        });

//        List<PrivilegeAcl> acls = privilegeAclService.getAclByRoleId(868497241403293729l);
//        Assert.notNull(acls);
//        acls.forEach(System.out::println);
    }


}