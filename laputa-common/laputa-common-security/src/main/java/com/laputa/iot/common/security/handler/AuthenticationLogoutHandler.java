package com.laputa.iot.common.security.handler;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sommer.jiang
 * @date 2021/06/22 退出后置处理
 */
public interface AuthenticationLogoutHandler {

	/**
	 * 业务处理
	 * @param authentication 认证信息
	 * @param request 请求信息
	 * @param response 响应信息
	 */
	void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response);

}
