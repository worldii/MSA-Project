package com.example.chatservice.exception;

public class ChatroomNotFoundException extends RuntimeException {

    private static final String MESSAGE = "CHATROOM_NOT_FOUND";

    public ChatroomNotFoundException() {
        super(MESSAGE);
    }
}
