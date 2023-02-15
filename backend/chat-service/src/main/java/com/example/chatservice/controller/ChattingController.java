package com.example.chatservice.controller;

import com.example.chatservice.dto.response.ChatroomMessageResponse;
import com.example.chatservice.dto.response.ChatroomResponse;
import com.example.chatservice.service.ChatMessageService;
import com.example.chatservice.service.ChatroomService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChattingController {

    private final ChatroomService chatroomService;

    private final ChatMessageService chatMessageService;

    @GetMapping("/chatrooms/{userId}")
    public ResponseEntity<ChatroomResponse> findChatrooms(@PathVariable Long userId) {
        ChatroomResponse response = chatroomService.findChatroomsByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chatrooms/{chatroomId}")
    public ResponseEntity<ChatroomMessageResponse> findChatMessages(@PathVariable String chatroomId) {
        ChatroomMessageResponse response = chatroomService.findChatMessagesByChatRoomId(chatroomId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/chatrooms/{userId}/{anotherId}")
    public ResponseEntity<?> createChatroom(@PathVariable Long userId, @PathVariable Long anotherId) {
        String result = chatroomService.createChatroom(userId, anotherId);
        if (result.equals("duplicated")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.created(URI.create("/chatrooms/" + userId + "/" + anotherId)).build();
    }

    @DeleteMapping("/chatrooms/{userId}/{chatroomId}")
    public ResponseEntity<?> deleteChatroom(@PathVariable Long userId, @PathVariable String chatroomId) {
        chatroomService.deleteChatroom(userId, chatroomId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/images/{chatroomId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> sendImage(@PathVariable Long chatroomId, @RequestBody MultipartFile file) {
        chatMessageService.sendImage(chatroomId, file);
        return null;
    }
}
