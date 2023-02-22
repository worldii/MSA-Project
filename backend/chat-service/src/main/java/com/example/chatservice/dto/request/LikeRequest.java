package com.example.chatservice.dto.request;

import lombok.Data;

@Data
public class LikeRequest {

    private String messageId;

    private Long userId;
}
