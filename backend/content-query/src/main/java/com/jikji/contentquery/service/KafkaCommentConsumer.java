package com.jikji.contentquery.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.Content;
import com.jikji.contentquery.repository.CommentRepository;
import com.jikji.contentquery.repository.ContentQueryRepository;
import com.jikji.contentquery.util.KafkaTopic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaCommentConsumer {
	private final ObjectMapper mapper;
	private final CommentRepository commentRepository;

	@KafkaListener(topics = KafkaTopic.ADD_COMMENT)
	public void addComment(String message) throws JsonProcessingException {
		Comment comment = readCommentByJson(message);
		commentRepository.save(comment);
	}
	private Comment readCommentByJson(String json) throws JsonProcessingException {
		log.info("[Kafka message]: " + json);
		return mapper.readValue(json, Comment.class);
	}
}
