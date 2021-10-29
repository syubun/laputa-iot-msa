package com.laputa.iot.test.kit;

import com.laputa.iot.common.core.util.SpringContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

/**
 * @author sommer.jiang
 * @date 2020/9/22
 * <p>
 * Mocke 工具类
 */
public class OAuthKit {

	/**
	 * mock 请求增加统一请求头
	 * @return
	 */
	public static RequestPostProcessor token() {
		return mockRequest -> {
			OAuth2ClientContext clientContext = SpringContextHolder.getBean(OAuth2ClientContext.class);
			String token = clientContext.getAccessToken().getValue();
			mockRequest.addHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer: %s", token));
			return mockRequest;
		};
	}

}
