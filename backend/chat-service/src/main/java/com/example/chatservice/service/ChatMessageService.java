package com.example.chatservice.service;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.domain.Chatroom;
import com.example.chatservice.domain.MessageLike;
import com.example.chatservice.domain.UserInfo;
import com.example.chatservice.dto.ChatMessageDto;
import com.example.chatservice.dto.request.ChatMessageRequest;
import com.example.chatservice.dto.request.MessageLikeRequest;
import com.example.chatservice.dto.response.MessageResponse;
import com.example.chatservice.dto.response.MessageResponse.MessageType;
import com.example.chatservice.dto.SimpleMessageDto;
import com.example.chatservice.exception.ChatMessageNotFoundException;
import com.example.chatservice.exception.ChatroomNotFoundException;
import com.example.chatservice.exception.MessageLikeDuplicatedException;
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
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {
    private final MessageLikeRepository messageLikeRepository;

    private final ChatMessageRepository chatMessageRepository;

    private final ChatroomRepository chatroomRepository;

    private final SimpMessageSendingOperations sMSOperations;

    public void send(ChatMessageRequest request) {
        final Chatroom chatroom = getChatroom(request.getChatroomId());
        final List<UserInfo> users = chatroom.getUserAll();

        validatedByJoinUser(users, request.getUserId());

        final ChatMessage savedMessage = chatMessageRepository.save(new ChatMessage(request));
        final MessageResponse response = new MessageResponse(new ChatMessageDto(savedMessage), MessageType.NEW);
        sMSOperations.convertAndSend("/sub/" + chatroom.getId(), response);
    }

    public void like(MessageLikeRequest request) {
        final ChatMessage message = getChatMessage(request.getMessageId());
        final Chatroom chatroom = getChatroom(message.getChatroomId());
        final List<UserInfo> users = chatroom.getUserAll();

        validated(users, request);
        messageLikeRepository.save(new MessageLike(request));

        MessageResponse messageResponse = new MessageResponse(
                new SimpleMessageDto(message, request.getUserId()), MessageType.LIKE);
        for (UserInfo user: users) {
            sMSOperations.convertAndSend("/sub/" + user.getId(), messageResponse);
        }
    }

    public void unlike(MessageLikeRequest request) {

    }

    public void sendImage(Long chatroomId, MultipartFile file) {
    }

    private void validated(List<UserInfo> users, MessageLikeRequest request) {
        validatedByJoinUser(users, request.getUserId());
        validatedByDuplicatedUser(request.getMessageId(), request.getUserId());
    }

    private void validatedByJoinUser(List<UserInfo> users, Long userId) {
        if (!Objects.equals(users.get(0).getId(), userId)
                && !Objects.equals(users.get(1).getId(), userId)) {
            throw new NotJoinChatroomException();
        }
    }

    private void validatedByDuplicatedUser(String messageId, Long userId) {
        if (messageLikeRepository.existsByMessageIdAndUserId(messageId, userId)) {
            throw new MessageLikeDuplicatedException();
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
