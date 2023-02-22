package com.jikji.contentcommand.service.content;


import com.jikji.contentcommand.client.HashtagFeignClient;
import com.jikji.contentcommand.dto.request.ContentCreateRequest;
import com.jikji.contentcommand.util.HashtagUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContentSaver {

    private final ContentCommandService contentService;

    private final HashtagFeignClient hashtagFeignClient;

    public Long save(final ContentCreateRequest request) {
        final List<String> tags = HashtagUtil.getHashtagInText(request.getText());
        final List<Long> hashtagIdByName = getHashtagIdByName(tags);
        return contentService.save(request.toEntity(hashtagIdByName), tags);
    }

    private List<Long> getHashtagIdByName(final List<String> tags) {
        if (!tags.isEmpty()) {
            HashMap<String, List<String>> hashtags = new HashMap<>();
            hashtags.put("hashtags", tags);
            return hashtagFeignClient.addHashtag(hashtags).getBody();
        }
        return new ArrayList<>();
    }
}
