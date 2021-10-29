package com.laputa.iot.upms.service.impl;


import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.org.api.client.StaffRemoteService;
import com.laputa.iot.org.api.entity.Staff;
import com.laputa.iot.upms.LaputaUpmsService;

import com.laputa.iot.upms.api.dto.UserInfo;
import com.laputa.iot.upms.api.entity.SysUser;
import com.laputa.iot.upms.entity.SysMenu;
import com.laputa.iot.upms.api.entity.SysRole;
import com.laputa.iot.upms.entity.SysUserRole;
import com.laputa.iot.common.core.constant.CommonConstants;
import io.jsonwebtoken.lang.Assert;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.jsf.el.WebApplicationContextFacesELResolver;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LaputaUpmsService.class)
public class SysUserServiceImplTest{

    @Autowired
    private StringEncryptor stringEncryptor;



    @Test
    @Ignore
    public void testEnvironmentProperties() {
        System.setProperty("jasypt.encryptor.password", "laputa");
        String password = "Laputa@123456";
        String encryptPwd = stringEncryptor.encrypt(password);
        System.out.println("加密:：" + encryptPwd);
        System.out.println("解密：" + stringEncryptor.decrypt(encryptPwd));
//        System.setProperty("jasypt.encryptor.password", "pigx");
//        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
//        System.out.println(stringEncryptor.encrypt("lengleng"));
//        //加密方法
//        System.out.println(stringEncryptor.encrypt("123456"));
//        //解密方法
//        System.out.println(stringEncryptor.decrypt("saRv7ZnXsNAfsl3AL9OpCQ=="));
//        System.out.println(stringEncryptor.encrypt("laputa"));
//        System.out.println(stringEncryptor.decrypt("rZHA4LW5hHmhLAAzJoFNag=="));
    }
    @Autowired
    SysUserServiceImpl sysUserService;

    @Autowired
    SysRoleServiceImpl sysRoleService;

    @Autowired
    SysUserRoleServiceImpl sysUserRoleService;
    @Autowired
    SysMenuServiceImpl menuService;

//    @Autowired
//    SysRoleMenuServiceImpl roleMenuService;

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Test
    @Ignore
    void saveUser() {
//       String salt = new Random()
      SysUser sysUser = SysUser.builder().email("sommer_jiang@163.com")
              .phone("13800000001")
              .username("admin").name("管理员").lockFlag(CommonConstants.STATUS_NORMAL).password("123456")
              .tenantId(1l).build();
        sysUser.setPassword(ENCODER.encode(sysUser.getPassword()));
        sysUserService.save(sysUser) ;
    }
    @Resource
    StaffRemoteService staffRemoteService;

    @Test
    @Ignore
    void getRemoteStaff(){
        R<List<Staff>> result = staffRemoteService.getAll();
        List<Staff>  staffs =  result.getData();
        staffs.stream().forEach(staff -> {
            System.out.println(staff);
            SysUser sysUser = new SysUser();
            sysUser.setAvatar(staff.getAvatar());
            sysUser.setName(staff.getName());
            String pinyin = PinyinUtil.getPinyin(staff.getName());
            pinyin = pinyin.replace(" ","");
            sysUser.setUsername(pinyin);
            sysUser.setGender(staff.getSex());
            sysUser.setEmail(staff.getEmail());
            sysUser.setPhone(staff.getMobile());
            sysUser.setPassword(ENCODER.encode("123456"));
            sysUser.setLockFlag(CommonConstants.STATUS_NORMAL);
            sysUserService.save(sysUser) ;
            SysUser newUser = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, sysUser.getUsername()));
            if(newUser!=null){
                SysUserRole userRole = new SysUserRole();
                if(staff.getJobGradeCode().contains("H")){
                    userRole.setRoleId(897040861508927553L);
                    userRole.setRolename("ORG_MANAGER");
                }else{
                    userRole.setRoleId(897040484357111841L);
                    userRole.setRolename("ORG_STAFF");
                }
                userRole.setUserId(newUser.getId());
                userRole.setUsername(newUser.getUsername());
                sysUserRoleService.save(userRole);
            }

        });

    }


    @Test
    void updateAvatar(){
        R<List<Staff>> result = staffRemoteService.getAll();
        List<Staff>  staffs =  result.getData();
        staffs.stream().forEach(staff -> {
            System.out.println(staff);
            SysUser sysUser = sysUserService.getById(staff.getUserId());
            sysUser.setGender(staff.getSex());
            sysUserService.updateUser(sysUser) ;

        });

    }

    @Test
    @Ignore
    void gerRoles() {
        sysUserService.list().stream().forEach(System.out::println);
        for (SysUser sysUser : sysUserService.list()) {
            for (SysRole sysRole : sysRoleService.list()) {
                SysUserRole userRole = new SysUserRole();
                //sommer 20210723
                userRole.setRoleId(sysRole.getId());
                userRole.setUserId(sysUser.getId());
                sysUserRoleService.save(userRole);
            }
        }


    }

    @Test
    @Ignore
    void saveRoles() {
        SysRole sysRole  =  SysRole.builder().
                code("TENANT_USER")
                .name("租户用户")
                .roleDesc("租户默认角色")
                .dsType(0)
                .dsScope("master")
                .build();

        sysRoleService.save(sysRole) ;
    }

    @Test
    void savemenus() {
//        SysMenu sysRole  =  SysMenu.builder()
//                .path("/system")
//                .name("System")
//                .component("LAYOUT")
//                .redirect("/system/account")
//                .parentId(CommonConstants.MENU_TREE_ROOT_ID)
//                .icon("ion:settings-outline")
//                .title("routes.demo.system.moduleName")
//                .build();
//        SysMenu menu  =  SysMenu.builder()
//                .path("/dashboard")
//                .name("Welcome")
//                .component("/dashboard/analysis/index")
//                .parentId(CommonConstants.MENU_TREE_ROOT_ID)
//                .icon("bx:bx-home")
//                .sort(1)
//                .affix(true)
//                .title("routes.dashboard.analysis")
//                .build();

//        SysMenu menu  =  SysMenu.builder()
//                .path("account_detail/:id")
//                .name("AccountDetail")
//                .component("/system/account/AccountDetail")
//                .parentId(869184907492982817L)
//                .icon("bx:bx-home")
//                .hideMenu(true)
//                .showMenu(false)
//                .ignoreKeepAlive(true)
//                .currentActiveMenu("/system/account")
//                .sort(202)
//                .title("routes.demo.system.account_detail")
//                .build();

        SysMenu menu  =  SysMenu.builder()
                .path("dept")
                .name("DeptManagement")
                .component("/system/dept/index")
                .parentId(869184907492982817L)
                .icon("bx:bx-home")
                .ignoreKeepAlive(true)
                .sort(205)
                .title("routes.demo.system.dept")
                .build();

        menuService.save(menu) ;
    }
    @Test
    void findRoles(){

       sysUserService.list().forEach(sysUser->{
//           sysRoleService.list().forEach(role->{
//               SysUserRole sysUserRole = new SysUserRole(sysUser.getId(),role.getId());
//               sysUserRoleService.save(sysUserRole);
//           });
           System.out.println(sysUser.getName());
            UserInfo userInfo = sysUserService.findUserInfo(sysUser);
           System.out.println(userInfo);
            Assert.notNull(userInfo);
        });

    }

    @Test
    void insertRoleMenu(){
//        menuService.list().forEach(menu->{
//            SysRoleMenu sysRoleMenu = new SysRoleMenu();
//            sysRoleMenu.setRoleId(886923984392683649L);
//            sysRoleMenu.setMenuId(menu.getId());
//            roleMenuService.save(sysRoleMenu);
//        });

    }


    @Test
    void removeById() {
        SysRole sysRole  =  SysRole.builder().
                code("COMMON_USER")
                .name("普通用户")
                .roleDesc("普通用户JUESE")
                .dsType(0)
                .dsScope("master")
                .build();

        sysRoleService.removeRoleById(868493846139371553l) ;
    }
}