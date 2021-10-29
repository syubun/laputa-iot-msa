/*
 *    Copyright (c) 2018-2025, Sommer 20200212 All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the laputapdm.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: Sommer (sommer_jiang@163.com)
 */

package com.laputa.iot.msg;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.SecurityConstants;
import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.msg.api.entity.ImUser;
import com.laputa.iot.msg.service.ImUserService;
import com.laputa.iot.upms.api.entity.SysUser;
import com.laputa.iot.upms.api.feign.RemoteUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author sommer
 * @date 2021/10/7
 * <p>
 */
@SpringBootTest(classes = LaputaMsgService.class)
public class LaputaMsgServiceTest {

    @Autowired
    RemoteUserService remoteUserService;
    @Autowired
    ImUserService imUserService;


    @Test
    public void initUser(){
        R<List<SysUser>> result = remoteUserService.all(SecurityConstants.FROM_IN);
        result.getData().forEach(user->{
            ImUser imUser = new ImUser();
            System.out.println(user.toNomalString());
            BeanCopyUtil.copyProperties(user,imUser);
            imUserService.save(imUser);
//            imUser.setAvatar(user.getAvatar());
//            imUser.setEmail(user.getEmail());
//            imUser.setUsername(user.getUsername());
//            imUser.setPhone(user.getPhone());
//            imUser.setGender(user.getGender());
//            imUser.setRemark(user.getRemark());
//            imUser.setId(user.getId());

        });

    }
}
