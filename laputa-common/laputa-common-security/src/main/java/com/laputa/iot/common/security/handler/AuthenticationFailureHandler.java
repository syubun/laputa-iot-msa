package com.laputa.iot.common.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sommer.jiang
 * @date 2020/03/25 token 发放失败处理
 */
public interface AuthenticationFailureHandler {

	/**
	 * 业务处理
	 * @param authenticationException 错误信息
	 * @param authentication 认证信息
	 * @param request 请求信息
	 * @param response 响应信息
	 */
	void handle(AuthenticationException authenticationException, Authentication authentication,
			HttpServletRequest request, HttpServletResponse response);

}
