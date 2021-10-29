package com.laputa.iot.msg.channel.base;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.msg.channel.websocket.SocketSessionRegistry;
import com.laputa.iot.msg.entity.MessageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * WebSocket消息通知服务
 * 1.   发送消息的时候，路径以 /broker 或者 /topic 开头
 * 2.   订阅消息的时候，如果是单个用户发送的消息，需要追加/user
 *
 * @version 2017-11-08.
 * @auth Licw
 */
@Service
public class WebSocketService {

    private static Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 将消息广播给所有用户
     * @param destination 用户订阅的地址
     * @param responseMessage 消息Bean
     */
    public <T> void sendToAll(String destination, R<T> responseMessage) {
        logger.info("Send To All User, [{}]：{}", destination, JSONUtil.toJsonStr(responseMessage));
        simpMessagingTemplate.convertAndSend(destination, responseMessage);
    }

    /**
     * 将消息广播给所有用户
     * @param destination 用户订阅的地址
     * @param messageBean 消息Bean
     */
    public void sendToAll(String destination, MessageBean messageBean) {
        sendToAll(destination, R.success(messageBean));
    }

    /**
     * 将消息广播给所有用户（默认接收地址"/broker/notice"）
     * @param messageBean
     */
    public void sendToAll(MessageBean messageBean) {
        sendToAll("/broker/notice", messageBean);
    }

    /**
     * 将消息发送给指定用户
     * @param destination 用户订阅的地址
     * @param messageBean 消息内容
     * @param targetUserId 用户ID
     */
    public void sentToUser(String destination, MessageBean messageBean, String targetUserId) {
        // 获取用户的SessionId
        Set<String> sessionIdSet = SocketSessionRegistry.getSessionIdSet(targetUserId);
        if (CollectionUtils.isEmpty(sessionIdSet)) {
            logger.warn("找不到用户[{}]的SessionId", targetUserId);
            return;
        }

        logger.info("Send To User[{}]：{}", destination, JSONUtil.toJsonStr(messageBean));
        R<MessageBean> responseMessage = R.success(messageBean);
        for (String sessionId : sessionIdSet) {
            logger.info("Sent To Session[{}]", sessionId);
            simpMessagingTemplate.convertAndSendToUser(sessionId, destination, responseMessage, createHeaders(sessionId));
        }
    }

    private static MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
