package com.example.chatservice.dto.response;

import com.example.chatservice.dto.ChatMessageDto;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class ChatroomMessageResponse {

    private String chatroomId;

    @Setter
    private List<ChatMessageDto> messages;

    public ChatroomMessageResponse(String chatroomId) {
        this.chatroomId = chatroomId;
    }
}
