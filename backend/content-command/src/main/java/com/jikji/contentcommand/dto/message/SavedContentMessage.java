package com.jikji.contentcommand.dto.message;

import com.jikji.contentcommand.domain.Content;
import com.jikji.contentcommand.domain.ImageUrl;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public SavedContentMessage(Content content) {
        this.contentId = content.getId();
        this.userId = content.getUserId();
        this.text = content.getText();
        this.likes = content.getLikes();
        this.imageUrl = content.getImageUrl();
        this.hashtags = content.getHashtags();
        this.visibleComments = content.getVisibleComments();
        this.visibleLikes = content.getVisibleLikes();
        this.createdAt = content.getCreatedAt();
        this.modifiedAt = content.getModifiedAt();
    }
}
