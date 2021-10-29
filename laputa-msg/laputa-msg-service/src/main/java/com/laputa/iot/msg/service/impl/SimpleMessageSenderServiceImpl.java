package com.laputa.iot.msg.service.impl;


import com.laputa.iot.msg.channel.base.EmailService;
import com.laputa.iot.msg.channel.base.SmsService;
import com.laputa.iot.msg.service.IMessageSenderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简单消息发送实现，线程同步发送
 */
@Service
public class SimpleMessageSenderServiceImpl implements IMessageSenderService {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

//    @Autowired
//    private MsgTemplateEOService msgTemplateEOService;

    @Override
    public void sendEmail(String email, String title, String content) {
        executorService.execute(new Thread(() -> emailService.sendEmail(email, title, content)));
    }

    @Override
    public void sendSMS(String mobile, String content) {
        executorService.execute(new Thread(() -> smsService.sendSms(mobile, content)));
    }

    @Override
    public void sendWebSocket() {
        // TODO
    }

    @Override
    public void sendMessage(String userId, String templateId, Map<String, Object> placeholderMap) {
        Assert.isTrue(StringUtils.isNotEmpty(userId), "用户ID不能为空");
        this.sendMessage(Arrays.asList(userId), templateId, placeholderMap);
    }

    @Override
    public void sendMessage(List<String> userIdList, String templateId, Map<String, Object> placeholderMap) {
//        Assert.isTrue(StringUtils.isNotEmpty(templateId), "消息模板编号不能为空");
//        MsgTemplateEO msgTemplateEO = msgTemplateEOService.get(templateId);
//        Assert.isTrue(msgTemplateEO != null, String.format("消息模板[%s]不存在", templateId));
//
//        MsgMessageEO msgMessageEO = new MsgMessageEO();
//        msgMessageEO.setMessageId(UUID.randomUUID());
//        msgMessageEO.setContent(MessageUtils.fillMessageTemplate(msgTemplateEO.getContent(), placeholderMap));
//        msgMessageEO.setSendTime(new Date());
//        msgMessageEO.setTargetType(MessageTargetTypeEnum.TO_USER.getValue());
//        msgMessageEO.setTemplateId(msgTemplateEO.getTemplateId());
//        msgMessageEO.setTitle(MessageUtils.fillMessageTemplate(msgTemplateEO.getTitle(), placeholderMap));
//
//        // 发送消息
//        if (CollectionUtils.isNotEmpty(msgTemplateEO.getChannelIdList())) {
//            for (String chanelId : msgTemplateEO.getChannelIdList()) {
//                IMessageSender iMessageSender = IMessageSenderFactory.instance(chanelId);
//                iMessageSender.send(userIdList, msgMessageEO);
//            }
//        }
    }

    @Override
    public void sendMessageByRoleId(List<String> roleIdList, String templateId, Map<String, Object> placeholderMap) {
        // TODO
    }

    @Override
    public void sendMessageByOfficeId(List<String> officeIdList, String templateId, Map<String, Object> placeholderMap) {
        // TODO
    }
}
