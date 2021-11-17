package com.japharr.eventdemo.service;

import com.japharr.eventdemo.model.MailData;
import com.japharr.eventdemo.model.NotificationData;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final Configuration configuration;
    private final JavaMailSender javaMailSender;

    public void sendMail(NotificationData data) throws IOException, TemplateException {
        log.info("sendMail: {}", data);
        var template = configuration.getTemplate(data.getTemplate() + ".ftl");
        var content = FreeMarkerTemplateUtils.processTemplateIntoString(template, data.getModel());

        log.info("Preparing message.");
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            var messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(data.getToEmail());
            messageHelper.setSubject(data.getSubject());
            messageHelper.setText(content, true);
        };
        log.info("Sending email to: {}", data.getToEmail());

        javaMailSender.send(mimeMessagePreparator);
    }
}
