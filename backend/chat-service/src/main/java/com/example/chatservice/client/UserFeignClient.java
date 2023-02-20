package com.example.chatservice.client;

import com.example.chatservice.dto.UserInfoDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "http://localhost:8000/user-service")
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    ResponseEntity<UserInfoDetailDto> getUserInfo(@PathVariable(value = "id") Integer userId);
}
