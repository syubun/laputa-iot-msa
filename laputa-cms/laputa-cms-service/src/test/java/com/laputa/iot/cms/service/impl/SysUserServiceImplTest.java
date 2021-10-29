package com.laputa.iot.cms.service.impl;


import com.laputa.iot.cms.LaputaCMSService;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LaputaCMSService.class)
public class SysUserServiceImplTest{

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testEnvironmentProperties() {
        System.out.println(stringEncryptor.encrypt("laputa"));
        System.out.println(stringEncryptor.decrypt("rZHA4LW5hHmhLAAzJoFNag=="));
    }

}