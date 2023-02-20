package com.example.chatservice.controller;

import com.example.chatservice.dto.request.ChatMessageRequest;
import com.example.chatservice.dto.request.LikeRequest;
import com.example.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChattingWSController {

    private final ChatMessageService chatMessageService;


    @MessageMapping("/messages")
    public void sendMessage(@Payload ChatMessageRequest chatMessageRequest) {
        chatMessageService.send(chatMessageRequest);
    }

    @MessageMapping("/messages/like")
    public void like(@Payload LikeRequest request) {
        chatMessageService.likeAndUnlike(request);
    }

}
