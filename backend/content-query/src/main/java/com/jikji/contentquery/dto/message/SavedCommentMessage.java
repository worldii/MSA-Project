package com.jikji.contentquery.dto.message;

import com.jikji.contentquery.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SavedCommentMessage {
    private String id;
    private Long commentId;
    private Long userId;
    private String userName;
    private String profileUrl;
    private String description;

    private Long postId;

    private String createdAt;
    private int likes;

    @Builder
    public Comment toIndex(){
        return Comment.builder()
                .commentId(commentId)
                .userId(userId)
                .userName(userName)
                .profileUrl(profileUrl)
                .description(description)
                .postId(postId)
                .createdAt(createdAt)
                .likes(likes)
                .build();
    }
}
