package com.example.chatservice.dto;

import com.example.chatservice.domain.ChatMessage;
import java.text.SimpleDateFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessageDto {

    private String messageId;

    private String text;

    private Long userId;

    private String createdAt;

    private Boolean isLiked;

    private long timestamp;

    public ChatMessageDto(ChatMessage chatMessage, Boolean isLiked) {
        this.messageId = chatMessage.getId();
        this.text = chatMessage.getText();
        this.userId = chatMessage.getSenderId();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.createdAt = simpleDateFormat.format(chatMessage.getTimestamp());
        this.timestamp = chatMessage.getTimestamp();
        this.isLiked = isLiked;
    }

    public ChatMessageDto(ChatMessage chatMessage) {
        this.messageId = chatMessage.getId();
        this.text = chatMessage.getText();
        this.userId = chatMessage.getSenderId();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.createdAt = simpleDateFormat.format(chatMessage.getTimestamp());
        this.timestamp = chatMessage.getTimestamp();
        this.isLiked = false;
    }
}
