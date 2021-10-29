package com.laputa.iot.msg.channel.base;


import com.laputa.iot.msg.channel.sms.impl.EmptySmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsService {

    @Autowired
    private EmptySmsSender emptySmsSender;

    public void sendSms(String mobile, String content) {
        emptySmsSender.sendSms(mobile, content);
    }

    public void sendSms(List<String> mobileList, String content) {
        emptySmsSender.sendSms(mobileList, content);
    }

}
