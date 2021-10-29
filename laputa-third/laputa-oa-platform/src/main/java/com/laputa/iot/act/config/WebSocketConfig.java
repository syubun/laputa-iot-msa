package com.laputa.iot.act.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.data.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Sommer.Jiang
 * <p>
 * WebSocket配置类
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	private final ResourceServerTokenServices tokenService;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

				// 判断是否首次连接请求
				if (StompCommand.CONNECT.equals(accessor.getCommand())) {

					// 处理租户
					String tenant = accessor.getFirstNativeHeader(CommonConstants.TENANT_ID);
					if (StrUtil.isNotBlank(tenant)) {
						TenantContextHolder.setTenantId(Long.parseLong(tenant));

					}
					else {
						TenantContextHolder.setTenantId(CommonConstants.TENANT_ID_1);
					}

					// 验证令牌信息
					String tokens = accessor.getFirstNativeHeader("Authorization");
					log.info("webSocket token is {}", tokens);
					if (StrUtil.isBlank(tokens)) {
						return null;
					}

					OAuth2Authentication auth2Authentication = tokenService.loadAuthentication(tokens.split(" ")[1]);
					if (ObjectUtil.isNotNull(auth2Authentication)) {
						SecurityContextHolder.getContext().setAuthentication(auth2Authentication);
						accessor.setUser(auth2Authentication::getName);
						return message;
					}
					else {
						return null;
					}
				}
				// 不是首次连接，已经成功登陆
				return message;
			}
		});
	}

//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		// 订阅Broker名称
//		///topic 代表发布广播    /user 代表点对点
//		registry.enableSimpleBroker("/user", "/topic");
//		// 全局使用的消息前缀
//		registry.setApplicationDestinationPrefixes("/app");
//		// 点对点使用的订阅前缀，默认是/user/
//		registry.setUserDestinationPrefix("/user/");
//		//   Use this for enabling a Full featured broker like RabbitMQ
//        /*
//        registry.enableStompBrokerRelay("/topic")
//                .setRelayHost("localhost")
//                .setRelayPort(61613)
//                .setClientLogin("guest")
//                .setClientPasscode("guest");
//        */
//	}


}
