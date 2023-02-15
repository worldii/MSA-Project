package com.example.chatservice.exception;

public class NotJoinChatroomException extends RuntimeException {

    private static final String MESSAGE = "NOT_JOIN_CHATROOM";

    public NotJoinChatroomException() {
        super(MESSAGE);
    }
}
