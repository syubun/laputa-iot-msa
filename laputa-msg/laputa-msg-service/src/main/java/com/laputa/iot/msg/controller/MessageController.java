package com.laputa.iot.msg.controller;


import com.alibaba.fastjson.JSON;
import com.laputa.iot.common.core.base.dto.R;

import com.laputa.iot.common.core.util.DateUtils;
import com.laputa.iot.msg.api.dto.ResponseMessage;
import com.laputa.iot.msg.entity.NotifyMessage;
import com.laputa.iot.msg.service.ImUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

@Slf4j
@Api(value = "message", tags = "消息管理服务")
@RestController
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessage;

    @Autowired
    private SimpUserRegistry userRegistry;

    @Autowired
    private ImUserService userService;


    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final ResponseMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final ResponseMessage message,
                                             final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(
                "Sending private message to user " + principal.getName() + ": "
                        + message.getContent())
        );
    }




    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public NotifyMessage sendMessage(@Payload NotifyMessage chatMessage) {
        log.info("sendMessage:"+chatMessage.toString());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public NotifyMessage addUser(@Payload NotifyMessage chatMessage,
                                 SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        log.info("adduser:"+chatMessage.toString());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }



    @SubscribeMapping("/subscribe/room")
    public void sub() {
        log.info("房间消息订阅");
    }

    @MessageMapping("/ws") //registerStompEndpoints方法配置的
    @SendTo("/topic/greetings")
    public void greeting(@Header("topic") String topic, @Headers Map<String, Object> headers) {
        log.info("connected successfully....");
        log.info(topic);
        log.info(JSON.toJSONString(headers));
        userRegistry.getUsers().stream()
                .map(u -> u.getName())
                .forEach(name->log.info(name));
    }

//    @MessageMapping("/message")
//    @SendToUser("/message")
//    public NotifyMessage handleSubscribe() {
//       log.info("订阅消息");
//        return new NotifyMessage("订阅消息");
//    }

    /**
     * 测试对指定用户发送消息方法
     * 点对点
     * 最终发送地址  /user/{userId}/message
     * @return
     */
    @RequestMapping(path = "/send/{userId}", method = RequestMethod.POST)
    public R sendToUser(@PathVariable("userId") Long userId, @RequestParam(value = "content",required= false) String content) {
//    	logger.info("当前在线人数:" + userRegistry.getUserCount());
        simpMessage.convertAndSendToUser(String.valueOf(userId), "/message", new NotifyMessage("test message"));
        if(StringUtils.isBlank(content)){
            content = "test";
        }
        simpMessage.convertAndSend("/task/1/admin/remind", new NotifyMessage(content));
        return R.ok(new NotifyMessage("goods"));
    }

    @MessageMapping({"/heartbeat"})
    @SendToUser({"/topic/heartbeat"})
    public R<Map> subscribeHeartBeat(Map<String,String> data) {
        Integer status = null;
        if(data.containsKey("status")){
            status = Integer.parseInt(data.get("status"));
        }
        if(data.containsKey("username")){
            String name  = data.get("username");
//            log.info(name+" : "+status);
            String date  = data.get("time");
            log.info(name+" : "+status + " ： " + DateUtils.formatAsDateTime(new Date(Long.parseLong(date))));
            userService.updateUserActiveStatus(name,status);
        }

//        if (auth2Authentication!=null) {
//
//            String principal =  auth2Authentication.getPrincipal().toString();
//            if(NumberHelper.longValueOfNil(principal)>0){
//              Long  hostId = Long.parseLong(principal);
//                if (status!=null){
//                    userService.updateUserActiveStatus(hostId,status);
//                }
//            }
//
//        }
        return R.ok(data);
    }
}
