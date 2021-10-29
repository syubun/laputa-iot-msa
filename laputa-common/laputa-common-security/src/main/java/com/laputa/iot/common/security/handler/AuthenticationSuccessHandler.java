package com.laputa.iot.common.security.handler;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sommer.jiang
 * @date 2020/03/25 token 发放成功处理
 */
public interface AuthenticationSuccessHandler {

	/**
	 * 业务处理
	 * @param authentication 认证信息
	 * @param request 请求信息
	 * @param response 响应信息
	 */
	void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response);

}
