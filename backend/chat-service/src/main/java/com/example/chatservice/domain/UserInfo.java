package com.example.chatservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {

    private Long id;

    private Boolean deleted;

    public UserInfo(Long userId) {
        this.id = userId;
        this.deleted = false;
    }

    public void delete() {
        this.deleted = true;
    }
}
