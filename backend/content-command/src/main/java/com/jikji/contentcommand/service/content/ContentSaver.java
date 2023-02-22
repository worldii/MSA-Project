package com.jikji.contentcommand.service.content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jikji.contentcommand.client.HashtagFeignClient;
import com.jikji.contentcommand.domain.Content;
import com.jikji.contentcommand.dto.message.ContentSearchMessage;
import com.jikji.contentcommand.dto.message.SavedContentMessage;
import com.jikji.contentcommand.dto.request.ContentCreateRequest;
import com.jikji.contentcommand.util.HashtagUtil;
import com.jikji.contentcommand.util.KafkaTopic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContentSaver {

    private final ContentCommandService contentService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final HashtagFeignClient hashtagFeignClient;

    public Long save(final ContentCreateRequest request) {
        final List<String> tags = HashtagUtil.getHashtagInText(request.getText());
        final List<Long> hashtagIdByName = getHashtagIdByName(tags);

        Content savedContent = contentService.save(request.toEntity(hashtagIdByName));
        sendMessage(new ContentSearchMessage(savedContent, tags), KafkaTopic.ADD_CONTENT_SEARCH);
        sendMessage(new SavedContentMessage(savedContent), KafkaTopic.ADD_CONTENT);
        return savedContent.getId();
    }

    private List<Long> getHashtagIdByName(final List<String> tags) {
        if (!tags.isEmpty()) {
            HashMap<String, List<String>> hashtags = new HashMap<>();
            hashtags.put("hashtags", tags);
            return hashtagFeignClient.addHashtag(hashtags).getBody();
        }
        return new ArrayList<>();
    }

    private void sendMessage(Object message, String topic) {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String data = null;
        try {
            data = writer.writeValueAsString(message);
            kafkaTemplate.send(topic, data);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        log.info("content kafka send - " + data);
    }
}
