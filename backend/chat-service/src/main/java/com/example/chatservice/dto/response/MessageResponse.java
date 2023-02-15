package com.example.chatservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

    private Object data;

    private MessageType type;

    public enum MessageType {
        LIKE, UNLIKE, SIGNAL, NEW
    }
}
