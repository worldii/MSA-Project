package com.example.searchservice.dto.response;

import com.example.searchservice.domain.UserIndex;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class UserResponse {

    private Long userId;

    private String name;

    private String nickname;


    public UserResponse(UserIndex userIndex) {
        this.userId = userIndex.getUserId();
        this.name = userIndex.getName();
        this.nickname = userIndex.getNickname();
    }
}
