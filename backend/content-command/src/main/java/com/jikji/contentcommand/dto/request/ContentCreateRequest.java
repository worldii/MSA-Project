package com.jikji.contentcommand.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jikji.contentcommand.domain.Content;
import com.jikji.contentcommand.domain.ImageUrl;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class ContentCreateRequest {

    @JsonProperty(required = true)
    private Long userId;

    private String text;

    private boolean visibleComments = true;

    private boolean visibleLikes = true;

    @JsonProperty(required = true)
    private List<ImageUrl> imageUrl;


    @Builder
    public ContentCreateRequest(Long userId, String text, boolean visibleComments,
                                boolean visibleLikes, List<ImageUrl> imageUrl) {
        this.userId = userId;
        this.text = text;
        this.visibleComments = visibleComments;
        this.visibleLikes = visibleLikes;
        this.imageUrl = imageUrl;
    }

    public Content toEntity(List<Long> tags) {
        return Content.builder()
                .text(text)
                .imageUrl(imageUrl)
                .userId(userId)
                .visibleComments(visibleComments)
                .visibleLikes(visibleLikes)
                .hashtags(tags)
                .build();
    }
}
