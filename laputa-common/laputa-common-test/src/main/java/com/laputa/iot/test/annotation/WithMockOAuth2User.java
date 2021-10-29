package com.laputa.iot.test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.laputa.iot.test.WithMockSecurityContextFactory;

import org.springframework.security.test.context.support.WithSecurityContext;

/**
 * @author sommer.jiang
 * @date 2020/9/22
 * <p>
 * WithMockOAuth2User 注解
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockSecurityContextFactory.class)
public @interface WithMockOAuth2User {

	/**
	 * 用户名
	 */
	String username() default "admin";

	/**
	 * 密码
	 */
	String password() default "123456";

	/**
	 * 租户编号 默认租户编号1
	 */
	long tenant() default 1l;

}