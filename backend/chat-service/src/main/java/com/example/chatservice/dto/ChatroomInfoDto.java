package com.example.chatservice.dto;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.domain.Chatroom;
import com.example.chatservice.util.StringUtil;
import java.text.SimpleDateFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatroomInfoDto {
    private String chatroomId;

    private Long anotherId;

    private String username;

    private String anotherImage;

    private String latestMessage;

    private long latestMsgTimestamp;

    private String latestMsgDate;

    public ChatroomInfoDto(Chatroom chatroom, ChatMessage message, UserInfoDto user, Long anotherId) {
        this.chatroomId = chatroom.getId();
        this.anotherId = anotherId;
        this.username = user.getName();
        this.anotherImage = "";
        this.latestMessage = StringUtil.limitText(message.getText());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.latestMsgDate = simpleDateFormat.format(message.getTimestamp());
        this.latestMsgTimestamp = message.getTimestamp();
    }
}
