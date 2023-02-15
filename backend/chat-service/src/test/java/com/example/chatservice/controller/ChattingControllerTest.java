package com.example.chatservice.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.domain.Chatroom;
import com.example.chatservice.dto.response.ChatroomMessageResponse;
import com.example.chatservice.dto.response.ChatroomResponse;
import com.example.chatservice.repository.ChatMessageRepository;
import com.example.chatservice.repository.ChatroomRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.stream.LongStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ChattingControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatroomRepository chatroomRepository;

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

    @DisplayName("사용자의 dm 목록을 조회한다")
    @Test
    void findChatrooms() {
        // given
        final long userId = 1;
        String api = "http://localhost:" + port + "/chatrooms/" + userId;

        // when
        ExtractableResponse<Response> response = get(api);
        ChatroomResponse body = response.body().as(ChatroomResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(body.getUserId()).isEqualTo(userId);
        assertThat(body.getChatrooms()).isNotNull();
    }

    @DisplayName("채팅방의 메시지를 조회한다")
    @Test
    void findChatMessages() {
        // given
        final long userId = 1L;
        Chatroom chatroom = chatroomRepository.findByUserIds(11L, userId)
                .orElseThrow(() -> new RuntimeException("NOT_FOUND_CHATROOM"));
        String chatroomId = chatroom.getId();
        String api = "http://localhost:" + port + "/chats/" + chatroomId;

        // when
        ExtractableResponse<Response> response = get(api);
        ChatroomMessageResponse body = response.body().as(ChatroomMessageResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(body).isNotNull();
        assertThat(body.getChatroomId()).isEqualTo(chatroomId);
    }

    static ExtractableResponse<Response> get(final String api) {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().get(api)
                .then().log().all()
                .extract();
    }

}