package com.laputa.iot.msg.channel.base;


import com.laputa.iot.common.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@Slf4j
public class EmailService {



//    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${laputa.from.mail}")
    private String fromEmail;

    @Value("${laputa.from.name}")
    private String fromName;

    public EmailService() {
    }

    public EmailService(final JavaMailSender javaMailSender, final String fromEmail, final String fromName) {
        this.javaMailSender = new JavaMailSenderImpl();
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }

    public void sendEmail(String email, String title, String content) {
        log.info("Send Email To:[{}], title:[{}], Content:[{}]", email, title, content);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.addHeader("Content-Type", "text/html;charset=UTF-8");

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(new InternetAddress(fromEmail, fromName, "UTF-8"));
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(content, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException("邮件发送失败，异常：" + e.getMessage());
        }
    }

    public void sendEmail(List<String> emailList, String title, String content) {
        log.info("Send Email To:[{}], title:[{}], Content:[{}]", emailList, title, content);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.addHeader("Content-Type", "text/html;charset=UTF-8");

            for (String email : emailList) {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom(new InternetAddress(fromEmail, fromName, "UTF-8"));
                helper.setTo(email);
                helper.setSubject(title);
                helper.setText(content, true);

                javaMailSender.send(mimeMessage);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BizException("邮件发送失败，异常：" + e.getMessage());
        }
    }
}
