package com.laputa.iot.common.jwt.server.configuration;


import com.laputa.iot.common.jwt.server.properties.AuthServerProperties;
import com.laputa.iot.common.jwt.server.utils.JwtTokenServerUtils;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 认证服务端配置
 *
 */
@EnableConfigurationProperties(value = {
        AuthServerProperties.class,
})
public class AuthServerConfiguration {
    @Bean
    public JwtTokenServerUtils getJwtTokenServerUtils(AuthServerProperties authServerProperties) {
        return new JwtTokenServerUtils(authServerProperties);
    }
}
