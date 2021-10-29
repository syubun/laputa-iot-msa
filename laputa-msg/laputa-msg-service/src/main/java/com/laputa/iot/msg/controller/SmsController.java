package com.laputa.iot.msg.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.exception.BizException;
import com.laputa.iot.msg.utils.SmsUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "sms")
public class SmsController {
    private final SmsUtils smsUtils;


    @PostMapping("send")
    R sendSms(String mobile, String code) {

        log.debug("手机号生成验证码成功:{},{}", mobile, code);
        SendSmsResponse response = null;
        try {
            response = smsUtils.sendSms(mobile, code);
            log.info(response.getCode() + "  " + response.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
            log.error("发射短消息失败");
            throw new BizException("发射短消息失败");
        }
        return R.ok(response);
    }
}
