package com.laputa.iot.msg.controller;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.msg.service.IMessageSenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping(value = "/message/sender")
@Api(description = "消息 - 发送消息")
@Slf4j
public class MsgSenderController {


    @Autowired
    private IMessageSenderService msgSenderService;

    @ApiOperation(value = "发送消息到指定用户")
    @PostMapping("/byUserId")
    public R sendMessageByUserId(String[] userIdList, String templateId, @RequestBody Map<String, Object> placeholderMap) {
        msgSenderService.sendMessage(Arrays.asList(userIdList), templateId, placeholderMap);
        return R.success();
    }

    @ApiOperation(value = "发送消息到指定用户")
    @PostMapping("/byRoleId")
    public R sendMessageByRoleId(String[] roleIdList, String templateId, @RequestBody Map<String, Object> placeholderMap) {
        msgSenderService.sendMessageByRoleId(Arrays.asList(roleIdList), templateId, placeholderMap);
        return R.success();
    }

    @ApiOperation(value = "发送消息到指定用户")
    @PostMapping("/byOfficeId")
    public R sendMessageByOfficeId(String[] officeIdList, String templateId, @RequestBody Map<String, Object> placeholderMap) {
        msgSenderService.sendMessageByOfficeId(Arrays.asList(officeIdList), templateId, placeholderMap);
        return R.success();
    }

    @ApiOperation(value = "发送邮件")
    @PostMapping("/sendEmail")
    public R sendEmail(String email, String title, String content) {
        msgSenderService.sendEmail(email, title, content);
        return R.success();
    }


    @ApiOperation(value = "发送邮件")
    @PostMapping("/sendSMS")
    public R sendSMS(String mobile, String content) {
        msgSenderService.sendSMS(mobile, content);
        return R.success();
    }


    @ApiOperation(value = "发送WebSocket")
    @PostMapping("/sendWebSocket")
    public R sendWebSocket() {
        msgSenderService.sendWebSocket();
        return R.success();
    }

}
