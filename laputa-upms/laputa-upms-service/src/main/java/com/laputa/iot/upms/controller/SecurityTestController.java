package com.laputa.iot.upms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 模拟资源
 * @author hongcheng
 */
@RestController
@RequestMapping("/test")
public class SecurityTestController {

    /**
     * @PreAuthorize("hasAnyAuthority('p1')")
     * 具体可以使用哪些方法，自己看  MethodSecurityExpressionRoot  这个类
     * */
    @PreAuthorize("hasAnyAuthority('p1')")
    @RequestMapping("/r1")
    public String test1(){
        return "这里是资源1，只能p1权限访问";
    }

    @PreAuthorize("hasAnyAuthority('p2')")
    @RequestMapping("/r2")
    public String test2(){
        return "这里是资源2，只能p2权限访问";
    }

    @PreAuthorize("hasAnyAuthority('p3')")
    @RequestMapping("/r3")
    public String test3(){
        return "这里是资源3，只能p3权限访问";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/r4")
    public String test4(){
        return "这里是资源4，只能admin角色访问";
    }

    @PreAuthorize("hasRole('ROLE_role1')")
    @RequestMapping("/r5")
    public String test5(){
        return "这里是资源5，只能role1角色访问";
    }

    @PreAuthorize("hasRole('role2')")
    @RequestMapping("/r6")
    public String test6(){
        return "这里是资源6，只能role2角色访问";
    }



    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/r7")
    public String test7(){
        return "这里是资源7，只要认证了就能访问，认证可以是登录，也可以是RememberMe。";
    }


    @PreAuthorize("hasAnyAuthority('p3','p1')")
    @RequestMapping("/r8")
    public String test8(){
        return "这里是资源8，只要有p1或者p3权限就访问";
    }


    @PreAuthorize("hasAnyAuthority('p2') and hasRole('role2')")
    @RequestMapping("/r9")
    public String test9(){
        return "这里是资源9，只有同时拥有role2角色和p2权限才能访问";
    }


    /* 返回结果
    {"authorities":[{"authority":"ROLE_admin"},{"authority":"ROLE_role1"},{"authority":"p1"},{"authority":"p2"},{"authority":"p3"}],"details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":"4ADC66B3D98E239C9B10AC04AD43BDE0"},"authenticated":true,"principal":{"password":null,"username":"zhangsan","authorities":[{"authority":"ROLE_admin"},{"authority":"ROLE_role1"},{"authority":"p1"},{"authority":"p2"},{"authority":"p3"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true},"credentials":null,"name":"zhangsan"}
    * */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/user")
    public Object getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authentication;
    }



}