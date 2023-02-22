package com.jikji.contentquery.dto.message;

import com.jikji.contentquery.domain.Content;
import com.jikji.contentquery.domain.ImageUrl;
import java.util.List;
import lombok.Data;

@Data
public class SavedContentMessage {

    private Long contentId;

    private Long userId;

    private String text;

    private int likes;

    private List<ImageUrl> imageUrl;

    private List<Long> hashtags;

    private Boolean visibleLikes;

    private Boolean visibleComments;

    private String createdAt;

    private String modifiedAt;

    public Content toIndex() {
        return Content.builder()
                .contentId(contentId)
                .userId(userId)
                .text(text)
                .likes(likes)
                .imageUrl(imageUrl)
                .hashtags(hashtags)
                .visibleComments(visibleComments)
                .visibleLikes(visibleLikes)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();
    }
}
