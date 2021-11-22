package com.japharr.eventdemo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
public class NotificationData {
    String toEmail;
    String subject;
    String template;
    Map<String, Object> model;
}
