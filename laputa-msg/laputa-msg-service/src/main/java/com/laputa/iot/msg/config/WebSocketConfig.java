package com.laputa.iot.msg.config;

import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启websocket的支持
 */
//@Configuration
public class WebSocketConfig {  
//    @Bean
    public ServerEndpointExporter serverEndpointExporter(){  
        return new ServerEndpointExporter();  
    }  
}  
