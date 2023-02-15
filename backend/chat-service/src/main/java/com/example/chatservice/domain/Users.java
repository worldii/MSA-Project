package com.example.chatservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Users {

    private UserInfo userA;

    private UserInfo userB;

    public Users(Long userA, Long userB) {
        this.userA = new UserInfo(userA);
        this.userB = new UserInfo(userB);

        if (userA < userB) {
            this.userA = new UserInfo(userB);
            this.userB = new UserInfo(userA);
        }
    }

    public Long getAnotherUserId(Long myId) {
        if (userA.getId().equals(myId)) {
            return userB.getId();
        }
        return userA.getId();
    }

    public UserInfo getMyUser(Long userId) {
        if (userA.getId().equals(userId)) {
            return userA;
        }
        return userB;
    }
}
