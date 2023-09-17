package com.example.chatservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.domain.Chatroom;
import com.example.chatservice.dto.response.ChatroomMessageResponse;
import com.example.chatservice.dto.response.ChatroomResponse;
import com.example.chatservice.repository.ChatMessageRepository;
import com.example.chatservice.repository.ChatroomRepository;
import java.util.stream.LongStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ChatroomServiceTest {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatroomRepository chatroomRepository;

    @Autowired
    private ChatroomService chatroomService;

    @BeforeEach
    void setUp() {
        LongStream.range(1, 10).forEach(n -> {
            Chatroom chatroom = new Chatroom(n, n + 10L);
            Chatroom saved = chatroomRepository.save(chatroom);
            ChatMessage message = new ChatMessage(saved.getId(), n, "text message" + n);
            chatMessageRepository.save(message);
        });
    }

    @AfterEach
    void tearDown() {
        chatroomRepository.deleteAll();
        chatMessageRepository.deleteAll();
    }

    @DisplayName("사용자 id로 dm 목록을 조회한다")
    @Test
    void findChatroomsByUserId() {
        // given
        final Long userId = 1L;

        // when
        ChatroomResponse chatrooms = chatroomService.findChatroomsByUserId(userId);

        // then
        assertThat(chatrooms.getUserId()).isEqualTo(userId);
        assertThat(chatrooms.getChatrooms()).isNotNull();
        assertThat(chatrooms.getChatrooms().get(0).getLatestMessage()).isEqualTo("text message1");
    }

    @DisplayName("각 채팅방의 메시지를 조회한다")
    @Test
    void findChatMessagesByChatRoomId() {
        // given
        final Long userId = 1L;
        Chatroom chatroom = chatroomRepository.findByUserIds(11L, 1L)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND_CHATROOM"));
        String chatroomId = chatroom.getId();

        // when
        ChatroomMessageResponse messages = chatroomService.findChatMessagesByChatRoomId(
            chatroomId, userId);

        // then
        assertThat(messages.getChatroomId()).isEqualTo(chatroomId);
        assertThat(messages).isNotNull();
    }

    @DisplayName("채팅방을 새로 생성한다")
    @Test
    void create() {
        // given
        Long myId = 1999L;
        Long anotherId = 20001L;

        // when
        String result = chatroomService.createChatroom(myId, anotherId);

        // then
        assertThat(result).isNotEqualTo("duplicated");
    }

    @DisplayName("채팅방은 중복 생성할 수 없다")
    @Test
    void create_duplicated() {
        // given
        Long myId = 1L;
        Long anotherId = 11L;

        // when
        String result = chatroomService.createChatroom(myId, anotherId);

        // then
        assertThat(result).isEqualTo("duplicated");
    }

    @DisplayName("사용자의 채팅방 목록에서 제거한다")
    @Test
    void delete() {
        // given
        Long myId = 1L;
        Chatroom chatroom = chatroomRepository.findByUserIds(11L, 1L)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND_CHATROOM"));
        String chatroomId = chatroom.getId();

        // when
        Chatroom result = chatroomService.deleteChatroom(myId, chatroomId);

        // then
        assertThat(result.getUsers().getMyUser(myId).getDeleted()).isTrue();
    }

    @DisplayName("사용자 둘다 채팅방을 삭제하면 db에서도 삭제된다")
    @Test
    void delete_all() {
        // given
        Long myId = 1L;
        Long anotherId = 11L;
        Chatroom chatroom = chatroomRepository.findByUserIds(anotherId, myId)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND_CHATROOM"));
        chatroom.offVisibility(anotherId);
        chatroomRepository.save(chatroom);

        // when
        Chatroom result = chatroomService.deleteChatroom(myId, chatroom.getId());

        // then
        assertThat(result).isNull();
    }

}
