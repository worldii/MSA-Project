package com.jikji.contentcommand.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jikji.contentcommand.client.HashtagFeignClient;
import com.jikji.contentcommand.domain.Content;
import com.jikji.contentcommand.dto.message.ContentKafkaMessage;
import com.jikji.contentcommand.dto.message.ContentSearchMessage;
import com.jikji.contentcommand.dto.message.HashtagKafkaMessage;
import com.jikji.contentcommand.dto.request.ContentCreateRequest;
import com.jikji.contentcommand.dto.request.ContentUpdateRequest;
import com.jikji.contentcommand.exception.ContentNotFoundException;
import com.jikji.contentcommand.repository.ContentCommandRepository;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jikji.contentcommand.util.KafkaTopic;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContentCommandServiceImpl implements ContentCommandService {

    private final ContentCommandRepository contentCommandRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final HashtagFeignClient hashtagFeignClient;

    @Override
    public Long save(final ContentCreateRequest request) {
        log.info(request.getText());
        HashMap<String, List<String>> hashtags = new HashMap<>();
        List<String> data = List.of(request.getHashtags().get(0).split(", "));
        hashtags.put("hashtags", data);

        final ResponseEntity<List<Long>> response = hashtagFeignClient.addHashtag(hashtags);

        Content savedContent = contentCommandRepository.save(request.toEntity(response.getBody()));
        sendMessage(savedContent, KafkaTopic.ADD_CONTENT);
        sendMessage(new ContentSearchMessage(savedContent, data));
        return savedContent.getId();
    }

    @Override
    public void update(final Long contentId, final ContentUpdateRequest request) {
        final Content content = contentCommandRepository.findById(contentId)
                .orElseThrow(ContentNotFoundException::new);
        content.update(request);
        contentCommandRepository.save(content);
        sendMessage(content, KafkaTopic.UPDATE_CONTENT);
        sendMessage(content.getHashtags(), contentId);
    }

    @Override
    public void delete(Long contentId) {
        contentCommandRepository.deleteById(contentId);
        kafkaTemplate.send(KafkaTopic.DELETE_CONTENT, String.valueOf(contentId));
    }

    public boolean visibilityLikes(Long contentId) {
        Content content = getContent(contentId);
        content.changeVisibleLikes();
        Content savedContent = contentCommandRepository.save(content);
        kafkaTemplate.send(KafkaTopic.VISIBLE_LIKES, String.valueOf(contentId));
        return savedContent.getVisibleLikes();
    }

    public boolean visibilityComments(Long contentId) {
        Content content = getContent(contentId);
        content.changeVisibleComments();
        Content savedContent = contentCommandRepository.save(content);
        kafkaTemplate.send(KafkaTopic.VISIBLE_COMMENT, String.valueOf(contentId));
        return savedContent.getVisibleComments();
    }

    private Content getContent(Long contentId) {
        return contentCommandRepository.findById(contentId)
                .orElseThrow(ContentNotFoundException::new);
    }

    private void sendMessage(Content content, String topic) {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String data = null;
        try {
            ContentKafkaMessage message = new ContentKafkaMessage(content);
            data = writer.writeValueAsString(message);
            kafkaTemplate.send(topic, data);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        log.info("content kafka send - " + data);
    }

    private void sendMessage(ContentSearchMessage message) {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String data = null;
        try {
            data = writer.writeValueAsString(message);
            kafkaTemplate.send(KafkaTopic.ADD_CONTENT_SEARCH, data);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        log.info("content kafka send - " + data);
    }

    private void sendMessage(List<Long> hashtags, Long contentId) {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String data = null;
        try {
            HashtagKafkaMessage message = new HashtagKafkaMessage(hashtags, contentId);
            data = writer.writeValueAsString(message);
            kafkaTemplate.send(KafkaTopic.UPDATE_HASHTAG, data);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        log.info("content kafka send - " + data);
    }

}