package com.japharr.eventdemo.controller;

import com.japharr.eventdemo.entity.User;
import com.japharr.eventdemo.model.UserDto;
import com.japharr.eventdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody UserDto userDto) {
        log.info("creating user: {}", userDto);
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}