package com.laputa.iot.upms.service.impl;

import com.laputa.iot.upms.LaputaUpmsService;
import com.laputa.iot.upms.entity.SysMenu;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = LaputaUpmsService.class)
class SysMenuServiceImplTest {

    @Autowired
    SysMenuServiceImpl sysMenuService;

    @Test
    void findMenuByRoleId() {
        List<SysMenu> menuByRoleId = sysMenuService.findMenuByRoleId(886923984392683649l);
        Assert.notNull(menuByRoleId);
    }
}