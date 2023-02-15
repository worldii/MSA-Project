package com.example.chatservice.domain;

import com.example.chatservice.dto.request.ChatMessageRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Field.Write;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "chatMessages")
public class ChatMessage {

    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    @Indexed(name = "chat_room_idx")
    @Field(write = Write.NON_NULL)
    private String chatroomId;

    @Indexed(name = "user_id_idx")
    @Field(write = Write.NON_NULL)
    private Long senderId;

    private String text;

    @Indexed(name = "created_date_idx", direction = IndexDirection.DESCENDING)
    @Field(write = Write.NON_NULL)
    @CreatedDate
    private Long timestamp;

    public ChatMessage(String chatroomId, Long senderId, String text) {
        this.chatroomId = chatroomId;
        this.senderId = senderId;
        this.text = text;
    }

    public ChatMessage(ChatMessageRequest request) {
        this.chatroomId = request.getChatroomId();
        this.senderId = request.getUserId();
        this.text = request.getText();
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
