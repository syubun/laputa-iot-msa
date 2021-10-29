package com.laputa.iot.msg.controller;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.msg.api.dto.ResponseMessage;
import com.laputa.iot.msg.service.WSService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WSController {

    @Autowired
    private WSService service;

    @PostMapping("/send-message")
    public R sendMessage(@RequestBody final ResponseMessage message) {
        service.notifyFrontend(message.getContent());
        return  R.ok();
    }

    @PostMapping("/send-private-message/{id}")
    public R sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final ResponseMessage message) {
        service.notifyUser(id, message.getContent());
       return   R.ok();
    }






}
