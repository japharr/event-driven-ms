package com.japharr.eventdemo.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class NotificationData {
    String toEmail;
    String subject;
    String template;
    Map<String, Object> model;
}
