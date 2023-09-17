package com.example.chatservice.service;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.domain.Chatroom;
import com.example.chatservice.domain.MessageLike;
import com.example.chatservice.domain.UserInfo;
import com.example.chatservice.dto.ChatMessageDto;
import com.example.chatservice.dto.request.ChatMessageRequest;
import com.example.chatservice.dto.request.LikeRequest;
import com.example.chatservice.dto.response.MessageResponse;
import com.example.chatservice.dto.response.MessageResponse.MessageType;
import com.example.chatservice.dto.SimpleMessageDto;
import com.example.chatservice.exception.ChatMessageNotFoundException;
import com.example.chatservice.exception.ChatroomNotFoundException;
import com.example.chatservice.exception.NotJoinChatroomException;
import com.example.chatservice.repository.ChatMessageRepository;
import com.example.chatservice.repository.ChatroomRepository;
import com.example.chatservice.repository.MessageLikeRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatMessageService {

    private final MessageLikeRepository messageLikeRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomRepository chatroomRepository;
    private final SimpMessageSendingOperations sMSOperations;

    @Transactional
    public void send(ChatMessageRequest request) {
        final Chatroom chatroom = getChatroom(request.getChatroomId());
        final List<UserInfo> users = chatroom.getUserAll();

        validatedByJoinUser(users, request.getUserId());

        final ChatMessage savedMessage = chatMessageRepository.save(new ChatMessage(request));
        final MessageResponse response = new MessageResponse(new ChatMessageDto(savedMessage), MessageType.NEW);
        sMSOperations.convertAndSend("/sub/" + chatroom.getId(), response);
    }

    @Transactional
    public void likeAndUnlike(LikeRequest request) {
        final ChatMessage message = getChatMessage(request.getMessageId());
        final Chatroom chatroom = getChatroom(message.getChatroomId());
        final List<UserInfo> users = chatroom.getUserAll();

        validatedByJoinUser(users, request.getUserId());

        final MessageType messageType = checkType(request);
        MessageResponse messageResponse = new MessageResponse(
                new SimpleMessageDto(message, request.getUserId()), messageType);
        for (UserInfo user: users) {
            sMSOperations.convertAndSend("/sub/" + user.getId(), messageResponse);
        }
    }

    private MessageType checkType(LikeRequest request) {
        if (Boolean.FALSE.equals(
            messageLikeRepository.existsByMessageIdAndUserId(request.getMessageId(), request.getUserId()))
        ) {
            messageLikeRepository.save(new MessageLike(request));
            return MessageType.LIKE;
        }
        messageLikeRepository.deleteByMessageIdAndUserId(request.getMessageId(), request.getUserId());
        return MessageType.UNLIKE;
    }

    private void validatedByJoinUser(List<UserInfo> users, Long userId) {
        if (!Objects.equals(users.get(0).getId(), userId)
                && !Objects.equals(users.get(1).getId(), userId)) {
            throw new NotJoinChatroomException();
        }
    }
    private Chatroom getChatroom(String chatroomId) {
        return chatroomRepository.findById(chatroomId)
                .orElseThrow(ChatroomNotFoundException::new);
    }

    private ChatMessage getChatMessage(String messageId) {
        return chatMessageRepository.findById(messageId)
                .orElseThrow(ChatMessageNotFoundException::new);
    }
}
