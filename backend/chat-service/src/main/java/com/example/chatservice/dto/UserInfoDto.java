package com.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

    private Long userId;

    private String profileImage;

    private String name;

    private String nickname;

    public UserInfoDto(final UserInfoDetailDto userInfoDetailDto) {
        this.userId = userInfoDetailDto.getId();
        this.profileImage = "";
        this.name = userInfoDetailDto.getName();
        this.nickname = userInfoDetailDto.getNickname();
    }
}
