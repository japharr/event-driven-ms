package com.japharr.eventdemo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
}
