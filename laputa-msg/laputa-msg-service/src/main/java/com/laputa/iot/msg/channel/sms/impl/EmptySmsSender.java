package com.laputa.iot.msg.channel.sms.impl;

import com.laputa.iot.common.core.exception.BizException;
import com.laputa.iot.msg.channel.sms.ISmSSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmptySmsSender implements ISmSSender {



    @Override
    public void sendSms(String mobile, String content) {
        log.info("Send SMS To:[{}], title:[{}], Content:[{}]", mobile, content);
        try {
            // 短信发送实现
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException("短信发送失败，异常：" + e.getMessage());
        }
    }

    @Override
    public void sendSms(List<String> mobileList, String content) {
        log.info("Send SMS To:[{}], title:[{}], Content:[{}]", mobileList, content);
        try {
            // 短信发送实现
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException("短信发送失败，异常：" + e.getMessage());
        }
    }
}
