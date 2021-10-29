package com.laputa.iot.upms.config;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

@Slf4j
@Data
@ToString
@Configuration
@ConfigurationProperties(prefix ="laputa.nacos")
public class LaputaNacos {
    /**
     * 维护租户列名称
     */
    private String ip = "127.0.0.1";

    /**
     * 多租户的数据表集合
     */
    private String port;

    private String namespace;


    @Async
    @Order
    @EventListener({ WebServerInitializedEvent.class})
    public void initCloud() {
        log.info(this.toString());
    }

}
