package com.laputa.iot.device.handler;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * <p>描述:配置channel1消息处理 </p>
 * 
 * @author xingyl
 * @date 2020年3月27日 下午6:33:35
 */
@Slf4j
@Component
public class Mqtt2MessageHandler implements MessageHandler {
	
	@ServiceActivator(inputChannel = "channel2")
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		log.info("channel2收到消息---{}", message);
	}

}
