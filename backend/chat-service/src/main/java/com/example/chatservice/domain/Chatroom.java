package com.example.chatservice.domain;

import java.util.Arrays;
import java.util.List;
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
@Document(collection = "chatrooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatroom {

    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    @Field(write = Write.NON_NULL)
    @Indexed(name = "userA_and_userB_idx", unique = true)
    private Users users;

    @Indexed(name = "crated_date_idx", direction = IndexDirection.DESCENDING)
    @Field(write = Write.NON_NULL)
    @CreatedDate
    private long latestDate;

    public Chatroom(Long userA, Long userB) {
        this.users = new Users(userA, userB);
    }

    public void offVisibility(Long userId) {
        UserInfo user = users.getMyUser(userId);
        user.delete();
    }

    public List<UserInfo> getUserAll() {
        return Arrays.asList(users.getUserA(), users.getUserB());
    }

    public boolean isDeletedAll() {
        return users.getUserA().getDeleted() && users.getUserB().getDeleted();
    }
}
