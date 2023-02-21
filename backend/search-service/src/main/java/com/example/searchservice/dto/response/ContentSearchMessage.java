package com.example.searchservice.dto.response;

import com.example.searchservice.domain.ContentIndex;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContentSearchMessage {

    private Long contentId;

    private List<String> hashtags;

    private int count;

    private Long createAt;

    public ContentIndex toIndex() {
        return ContentIndex.builder()
                .contentId(contentId)
                .count(count)
                .createdAt(createAt)
                .hashtags(hashtags)
                .build();
    }
}
