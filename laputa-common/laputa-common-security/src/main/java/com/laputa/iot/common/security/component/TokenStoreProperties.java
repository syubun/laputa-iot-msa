package com.laputa.iot.common.security.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;


/**
 * Token配置
 * @author sommer.jiang
 * @date 2021/5/19
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "laputa.oauth2.token.store")
@RefreshScope
public class TokenStoreProperties {
    /**
     * token存储类型(redis/db/authJwt/resJwt)
     */
    private String type = "redis";
}
