package com.example.chatservice.service;

import com.example.chatservice.client.UserFeignClient;
import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.domain.Chatroom;
import com.example.chatservice.dto.ChatMessageDto;
import com.example.chatservice.dto.ChatroomInfoDto;
import com.example.chatservice.dto.response.ChatroomMessageResponse;
import com.example.chatservice.dto.response.ChatroomResponse;
import com.example.chatservice.dto.UserInfoDetailDto;
import com.example.chatservice.dto.UserInfoDto;
import com.example.chatservice.exception.ChatroomNotFoundException;
import com.example.chatservice.repository.ChatMessageRepository;
import com.example.chatservice.repository.ChatroomRepository;
import com.example.chatservice.repository.MessageLikeRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatroomService {
    private final MessageLikeRepository messageLikeRepository;

    private final ChatMessageRepository chatMessageRepository;

    private final ChatroomRepository chatroomRepository;

    private final UserFeignClient userFeignClient;


    public ChatroomResponse findChatroomsByUserId(Long userId) {
        List<Chatroom> chatrooms = chatroomRepository.findAllByUsers(userId);
        ChatroomResponse chatroomResponse = new ChatroomResponse(userId);
        List<ChatroomInfoDto> chatroomInfos = new ArrayList<>();

        for(Chatroom chatroom: chatrooms) {
            ChatMessage message = chatMessageRepository.findTop1ByChatroomIdOrderByTimestampDesc(chatroom.getId())
                    .orElse(createDummyMessage(chatroom.getId(), userId));
            chatroomInfos.add(createChatroomInfo(message, chatroom, userId));
        }

        chatroomResponse.setChatrooms(chatroomInfos);
        return chatroomResponse;
    }

    public ChatroomMessageResponse findChatMessagesByChatRoomId(String chatroomId, Long userId) {
        List<ChatMessage> messages = chatMessageRepository.findAllByChatroomId(chatroomId);
        ChatroomMessageResponse chatroomMessageResponse = new ChatroomMessageResponse(chatroomId);
        List<ChatMessageDto> chatMessageInfos = new ArrayList<>();

        messages.forEach(chatMessage -> {
            Boolean exists = messageLikeRepository.existsByMessageIdAndUserId(chatMessage.getId(), userId);
            chatMessageInfos.add(new ChatMessageDto(chatMessage, exists));
        });

        chatroomMessageResponse.setMessages(chatMessageInfos);
        return chatroomMessageResponse;
    }

    public String createChatroom(Long myId, Long anotherId) {
        Chatroom chatroom = new Chatroom(myId, anotherId);
        if (findUserIds(myId, anotherId) != 0) {
            return "duplicated";
        }

        Chatroom saved = chatroomRepository.save(chatroom);
        return saved.getId();
    }

    public Chatroom deleteChatroom(Long userId, String chatroomId) {
        Chatroom chatroom = chatroomRepository.findByUserIdAndId(userId, chatroomId)
                .orElseThrow(ChatroomNotFoundException::new);
        chatroom.offVisibility(userId);

        if (chatroom.isDeletedAll()) {
            chatroomRepository.delete(chatroom);
            chatMessageRepository.deleteByChatroomId(chatroomId);
            return null;
        }

        return chatroomRepository.save(chatroom);
    }

    private Integer findUserIds(Long userA, Long userB) {
        if (userA < userB) {
            return chatroomRepository.countAllByUsers(userB, userA);
        }
        return chatroomRepository.countAllByUsers(userA, userB);
    }

    private UserInfoDetailDto getUserInfoDetail(Long userId) {
        return userFeignClient.getUserInfo(userId.intValue()).getBody();
    }

    private ChatroomInfoDto createChatroomInfo(ChatMessage message, Chatroom chatroom, Long userId) {
        final Long anotherUserId = chatroom.getUsers().getAnotherUserId(userId);
        final UserInfoDetailDto anotherUserInfo = getUserInfoDetail(anotherUserId);
        UserInfoDto user = new UserInfoDto(anotherUserInfo);
        return new ChatroomInfoDto(chatroom, message, user, anotherUserId);
    }

    private ChatMessage createDummyMessage(String chatroomId, Long userId) {
        ChatMessage dummyMessage = new ChatMessage(chatroomId, userId, "대화를 시작합니다!");
        dummyMessage.setTimestamp(System.currentTimeMillis());
        return dummyMessage;
    }
}
