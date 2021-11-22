package com.japharr.eventdemo.service;

import com.japharr.eventdemo.entity.User;
import com.japharr.eventdemo.model.UserDto;
import com.japharr.eventdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final StreamBridge streamBridge;

    @Transactional
    public void createUser(UserDto userDto) {
        log.info("creating user: {}", userDto);
        var user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
        userRepository.save(user);
        log.info("sending user to kafka stream...");
        streamBridge.send("supplier-out-0", userDto);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
