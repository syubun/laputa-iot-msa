package com.laputa.iot.msg.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.laputa.iot.common.core.base.dto.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @ClassName WebSocketInterceptor
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/5/29 17:15
 * @Version 1.0.0
 **/
@Component
@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.info("---------beforeHandshake---------principal:{}",SecurityContextHolder.getContext().getAuthentication().toString());
        log.info("Request URI:"+serverHttpRequest.getURI());

        boolean flag = true;
        // 获得请求参数
//        HashMap<String, String> paramMap = (HashMap<String, String>) HttpUtil.decodeParamMap(serverHttpRequest.getURI().getQuery(), CharsetUtil.CHARSET_UTF_8);
//        String token = paramMap.get("token");
//        if(token != null && !"".equals(token)) {
//            //验证token
//            String adminId = stringRedisTemplate.opsForValue().get(token);
//            if (StrUtil.isNotBlank(adminId)) {
//                String realToken = stringRedisTemplate.opsForValue().get(adminId);
//                flag =  token.equals(realToken);
//            }
//        }
        serverHttpResponse.getHeaders().setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        if (!flag) {
            log.info("User");
            serverHttpResponse.getBody().write(JSONObject.toJSONBytes(R.fail("获取Token失败")));
        }
        return flag;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("---------afterHandshake---------principal:{}",SecurityContextHolder.getContext().getAuthentication().toString());
        try {
            serverHttpResponse.getBody().write(JSONObject.toJSONBytes(R.success("WebSocket连接成功!")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
