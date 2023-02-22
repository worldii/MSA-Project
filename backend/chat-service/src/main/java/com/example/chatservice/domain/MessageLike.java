package com.example.chatservice.domain;


import com.example.chatservice.dto.request.LikeRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Field.Write;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Document(collection = "messageLikes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@CompoundIndexes({
        @CompoundIndex(name = "msg_user_idx", def = "{'messageId': -1, 'userId': -1}", unique = true)
})
public class MessageLike {

    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    @Field(write = Write.NON_NULL)
    private String messageId;

    @Field(write = Write.NON_NULL)
    private Long userId;

    public MessageLike(LikeRequest request) {
        this.messageId = request.getMessageId();
        this.userId = request.getUserId();
    }
}
