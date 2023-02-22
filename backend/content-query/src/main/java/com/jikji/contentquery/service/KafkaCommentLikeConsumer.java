package com.jikji.contentquery.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.CommentLikes;
import com.jikji.contentquery.domain.Content;
import com.jikji.contentquery.exception.CustomException;
import com.jikji.contentquery.exception.ErrorCode;
import com.jikji.contentquery.repository.CommentLikesRepository;
import com.jikji.contentquery.repository.CommentRepository;
import com.jikji.contentquery.util.KafkaTopic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaCommentLikeConsumer {

	private final ObjectMapper mapper;
	private final CommentLikesRepository commentLikesRepository;

	@KafkaListener(topics = KafkaTopic.ADD_COMMENT_LIKE)
	public void likeComment(String message) throws JsonProcessingException {
		CommentLikes commentLikes = readCommentByJson(message);
		commentLikesRepository.save(commentLikes);
	}



	@KafkaListener(topics = KafkaTopic.DELETE_COMMENT_LIKE)
	public void unlikeComment(String message) throws JsonProcessingException {
		CommentLikes commentLikes = mapper.readValue(message, CommentLikes.class);
		log.info("[Kafka message]: " + message);
		CommentLikes storedComment = commentLikesRepository.findByCommentLikeId(commentLikes.getCommentLikeId())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));
		commentLikesRepository.delete(storedComment);
	}
	private CommentLikes readCommentByJson(String json) throws JsonProcessingException {
		log.info("[Kafka message]: " + json);
		return mapper.readValue(json, CommentLikes.class);
	}
}
