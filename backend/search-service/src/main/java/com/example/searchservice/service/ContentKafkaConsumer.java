package com.example.searchservice.service;

import com.example.searchservice.dto.response.ContentSearchMessage;
import com.example.searchservice.repository.ContentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentKafkaConsumer {

    private final ContentRepository contentRepository;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "content-search-add")
    public void addContentInfo(String message) throws JsonProcessingException {
        log.info(message);
        final ContentSearchMessage contentInfo = objectMapper.readValue(message, ContentSearchMessage.class);
        contentRepository.save(contentInfo.toIndex());
    }
}
