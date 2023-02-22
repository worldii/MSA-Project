package com.jikji.contentcommand.dto.message;

import com.jikji.contentcommand.domain.Content;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContentSearchMessage {

    private Long contentId;

    private List<String> hashtags;

    private int count;

    private Long createAt;

    @Builder
    public ContentSearchMessage(Content content, List<String> tags) {
        this.contentId = content.getId();
        this.hashtags = tags;
        this.count = content.getLikes();
        this.createAt = content.getTimeStamp();
    }
}
