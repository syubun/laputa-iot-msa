package com.laputa.iot.common.jwt.server;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.laputa.iot.common.jwt.server.configuration.AuthServerConfiguration;

import org.springframework.context.annotation.Import;

/**
 * 启用 认证服务 的服务端配置
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthServerConfiguration.class)
@Documented
@Inherited
public @interface EnableAuthServer {
}
