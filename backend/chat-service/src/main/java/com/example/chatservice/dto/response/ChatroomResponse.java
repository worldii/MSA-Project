package com.example.chatservice.dto.response;

import com.example.chatservice.dto.ChatroomInfoDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatroomResponse {

    private Long userId;

    @Setter
    private List<ChatroomInfoDto> chatrooms;

    public ChatroomResponse(Long userId) {
        this.userId = userId;
    }

}
