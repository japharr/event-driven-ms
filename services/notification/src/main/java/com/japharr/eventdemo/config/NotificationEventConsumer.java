package com.japharr.eventdemo.config;

import com.japharr.eventdemo.model.NotificationData;
import com.japharr.eventdemo.model.UserDto;
import com.japharr.eventdemo.service.EmailService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventConsumer {
    private final EmailService emailService;

    @Bean
    public Consumer<UserDto> eventConsumer() {
        return user -> {
            log.info("Received new message from kafka topic");
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            var data = NotificationData.builder()
                .subject("Account confirmation")
                .toEmail(user.getEmail())
                .model(model)
                .template("account-confirmation")
                .build();

            try {
                emailService.sendMail(data);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        };
    }
}