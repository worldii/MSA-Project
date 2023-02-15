package com.example.chatservice.dto.request;

import lombok.Data;

@Data
public class ChatMessageRequest {

    private String chatroomId;

    private Long userId;

    private String text;
}
