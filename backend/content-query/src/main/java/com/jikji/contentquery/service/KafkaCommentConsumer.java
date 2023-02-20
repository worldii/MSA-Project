package com.jikji.contentquery.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.exception.CustomException;
import com.jikji.contentquery.exception.ErrorCode;
import com.jikji.contentquery.repository.CommentRepository;
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

	@KafkaListener(topics = KafkaTopic.DELETE_COMMENT)
	public void deleteComment(String message) throws JsonProcessingException {
		log.info("[Kafka message]: " + message);
		Long commentId = mapper.readValue(message, Long.class);
		Comment storedComment = commentRepository.findByCommentId(commentId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));
		commentRepository.delete(storedComment);
	}

	private Comment readCommentByJson(String json) throws JsonProcessingException {
		log.info("[Kafka message]: " + json);
		return mapper.readValue(json, Comment.class);
	}

}
