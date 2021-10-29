package com.laputa.iot.msg.channel.websocket;


import com.laputa.iot.common.core.util.SpringContextHolder;
import com.laputa.iot.common.data.utils.RedisUtils;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Set;

public class SocketSessionRegistry {

    private static final String PREFIX = "_WEB_SOCKET";

    private static RedisUtils redisUtil;

    public SocketSessionRegistry() {
    }

    private static RedisUtils getRedisUtil () {
        if(redisUtil == null) {
            redisUtil = SpringContextHolder.getBean(RedisUtils.class);
        }
        return redisUtil;
    }

    /**
     * 获取sessionId
     *
     * @param userId
     * @return
     */
    public static Set<String> getSessionIdSet(String userId) {
        Set set = getRedisUtil().sGet(userId + PREFIX);
        return set != null ? set : Collections.emptySet();
    }

    /**
     * register session
     *
     * @param userId
     * @param sessionId
     */
    public static void registerSessionId(String userId, String sessionId) {
        Assert.notNull(userId, "User must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        getRedisUtil().hSet(userId + PREFIX,"session", sessionId);
        redisUtil.expire(userId + PREFIX, 1000L);
    }

    public static void unRegisterSessionId(String userId, String sessionId) {
        Assert.notNull(userId, "User Name must not be null");
        Assert.notNull(sessionId, "Session ID must not be null");
        getRedisUtil().hDel(userId + PREFIX,sessionId);
    }
}