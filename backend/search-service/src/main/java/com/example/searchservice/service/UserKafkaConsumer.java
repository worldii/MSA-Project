package com.example.searchservice.service;

import com.example.searchservice.repository.UserRepository;
import com.example.searchservice.dto.UserInfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserKafkaConsumer {
    private final ObjectMapper mapper;

    private final UserRepository userRepository;

    @KafkaListener(topics = "join-user")
    public void addNewUserInfo(String message) throws JsonProcessingException {
        log.info(message);
        UserInfoDto userInfoDto = mapper.readValue(message, UserInfoDto.class);
        userRepository.save(userInfoDto.toIndex());
    }
}
