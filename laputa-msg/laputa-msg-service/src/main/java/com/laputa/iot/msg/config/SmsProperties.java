package com.laputa.iot.msg.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sommer
 * @Time: 2020-10-22 18:34
 * @Feature: 短信服务实体类
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "laputa.sms")
public class SmsProperties {


    private String accessKeyId;


    private String accessKeySecret;


    private String signName;


    private String verifyCodeTemplate;


}
