package com.laputa.iot.msg.service.impl;



import com.laputa.iot.msg.api.dto.ResponseMessage;
import com.laputa.iot.msg.service.WSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSServiceImpl implements WSService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WSServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);

        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final String message) {
        ResponseMessage response = new ResponseMessage(message);

        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", response);
    }
}
