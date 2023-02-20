package com.example.searchservice.dto;

import com.example.searchservice.domain.UserIndex;
import lombok.Data;

@Data
public class UserInfoDto {

    private Long userId;

    private String name;

    private String nickname;

    private String createdAt;

    public UserIndex toIndex() {
        return UserIndex.builder()
                .userId(userId)
                .name(name)
                .nickname(nickname)
                .createdAt(createdAt)
                .build();
    }
}
