package com.example.chatservice.exception;

public class ChatMessageNotFoundException extends RuntimeException {

    private static final String MESSAGE = "CHAT_MESSAGE_NOT_FOUND";

    public ChatMessageNotFoundException() {
        super(MESSAGE);
    }
}
