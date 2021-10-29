package com.laputa.iot.msg.listener;

import com.laputa.iot.common.data.tenant.TenantContextHolder;
import com.laputa.iot.msg.entity.NotifyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by sommer.jiang
 * on 25/07/17.
 */
@Component
@Slf4j
public class WebSocketEventListener {

//    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Laputa-IOT Received a new web socket connection");
        // 订阅通道 /task/租户ID/用户名称/remind
//        String target = String.format("/task/%s/%s/remind", TenantContextHolder.getTenantId(),
//                SecurityUtils.getUser().getUsername());

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getUser().getName();
        if(username != null) {
            log.info("Laputa-IOT User connected : " + username);

//            NotifyMessage chatMessage = new NotifyMessage();
//            chatMessage.setType(NotifyMessage.MessageType.LEAVE);
//            chatMessage.setSender(username);
//            String target = String.format("/task/%s/%s/remind", TenantContextHolder.getTenantId(),
//                    username);
//            log.info(target);
//            messagingTemplate.convertAndSend(target, "Test");
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        log.info("Laputa-IOT User Disconnected  " );
        if(username != null) {
            log.info("Laputa-IOT User Disconnected : " + username);

            NotifyMessage chatMessage = new NotifyMessage();
            chatMessage.setType(NotifyMessage.MessageType.LEAVE);
            chatMessage.setSender(username);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
