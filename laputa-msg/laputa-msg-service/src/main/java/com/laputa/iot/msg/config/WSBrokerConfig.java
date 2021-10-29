package com.laputa.iot.msg.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.data.tenant.TenantContextHolder;
import com.laputa.iot.msg.handler.LaputaHandshakeHandler;
import com.laputa.iot.msg.interceptor.WebSocketInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author sommer.jiang
 * <p>
 * WebSocket配置类
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WSBrokerConfig implements WebSocketMessageBrokerConfigurer {

	private final ResourceServerTokenServices tokenService;
	@Autowired
	private LaputaHandshakeHandler laputaHandshakeHandler;

	@Autowired
	private WebSocketInterceptor webSocketInterceptor;


	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
//   .setAllowedOrigins("http://localhost:3000",
//				"chrome-extension://ggnhohnkfcpcanfekomdkjffnfcjnjam")
		// 配置客户端尝试连接地址
		registry.
				addEndpoint("/ws").     // 设置连接节点，前端请求的建立连接的地址就是 http://ip:端口/ws
				addInterceptors(webSocketInterceptor).     // 设置握手拦截器
				setAllowedOriginPatterns("*").     // 配置跨域
				setHandshakeHandler(laputaHandshakeHandler).
				withSockJS();       // 开启sockJS支持，这里可以对不支持stomp的浏览器进行兼容。
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


	/**
	 * 复写了 configureMessageBroker() 方法：
	 * 配置了一个 简单的消息代理，通俗一点讲就是设置消息连接请求的各种规范信息。
	 * 发送应用程序的消息将会带有 “/app” 前缀。
	 * @param registry
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 消息代理,这里配置自带的消息代理，也可以配置其它的消息代理
		// 一定要注意这里的参数，可以理解为开启通道,后面如果使用了"/XXX"来作为前缀，这里就要配置,同时这里的"/topic"是默认群发消息的前缀,前端在订阅群发地址的时候需要加上"/topic"
		//定义了一个（或多个）客户端订阅地址的前缀信息，也就是客户端接收服务端发送消息的前缀信息
		registry.enableSimpleBroker("/topic","/user")
		.setHeartbeatValue(new long[]{10000L, 10000L})
		.setTaskScheduler(new DefaultManagedTaskScheduler());
		// 客户端向服务端发送消息需有的前缀,需要什么样的前缀在这里配置,但是不建议使用，这里跟下面首次订阅返回会有点冲突，如果不需要首次订阅返回消息，也可以加上消息前缀
		//定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀
		// 将应用的前缀设置为“/app”。所有目的地以“/app”打头的消息都将会路由到带有@MessageMapping @SubscribeMapping注解的方法中，而不会发布到代理队列或主题中。
		registry.setApplicationDestinationPrefixes("/app");
		// 配置单发消息的前缀 /user，前端订阅一对一通信的地址需要加上"/user"前缀，不设置的话，默认也是/user/
		registry.setUserDestinationPrefix("/user");
	}

	@Bean
	public WebSocketInterceptor getWebSocketInterceptor() {
		return new WebSocketInterceptor();
	}

}
