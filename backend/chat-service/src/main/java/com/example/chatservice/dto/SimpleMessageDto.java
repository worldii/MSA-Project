package com.example.chatservice.dto;

import com.example.chatservice.domain.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMessageDto {

    private String chatroomId;

    private String messageId;

    private Long userId;

    public SimpleMessageDto(ChatMessage message, Long userId) {
        this.chatroomId = message.getChatroomId();
        this.messageId = message.getId();
        this.userId = userId;
    }
}
