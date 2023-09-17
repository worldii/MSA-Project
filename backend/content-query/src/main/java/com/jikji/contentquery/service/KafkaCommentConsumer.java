package com.jikji.contentquery.service;

import static com.jikji.contentquery.exception.ErrorCode.NOT_FOUND_COMMENT;

import com.jikji.contentquery.dto.message.SavedCommentMessage;
import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.Content;
import com.jikji.contentquery.exception.CustomException;
import com.jikji.contentquery.exception.ErrorCode;
import com.jikji.contentquery.repository.CommentRepository;
import com.jikji.contentquery.util.KafkaTopic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
// TODO : 실패 했을 떄 재시도 로직 추가
public class KafkaCommentConsumer {
	private final ObjectMapper mapper;
	private final CommentRepository commentRepository;

	@KafkaListener(topics = KafkaTopic.ADD_COMMENT)
	public void addComment(String message) throws JsonProcessingException {
		SavedCommentMessage comment = readCommentByJson(message);
		commentRepository.save(comment.toIndex());
	}

	@KafkaListener(topics = KafkaTopic.INCREASE_COMMENT_LIKES)
	public void increaseCommentLikes(String message) throws JsonProcessingException {
		SavedCommentMessage comment = readCommentByJson(message);

		Comment updateComment = commentRepository
			.findByCommentId(comment.getCommentId())
			.orElseThrow(()-> new CustomException(NOT_FOUND_COMMENT));
		updateComment.increaseLikes();
		// TODO : 몽고 디비에서는 변경 감지 불가능?? JPA 에서는 변경 감지 가능
		commentRepository.save(updateComment);
	}

	@KafkaListener(topics = KafkaTopic.DECREASE_COMMENT_LIKES)
	public void decreaseCommentLikes(String message) throws JsonProcessingException {
		SavedCommentMessage comment = readCommentByJson(message);

		Comment updateComment = commentRepository
			.findByCommentId(comment.getCommentId())
			.orElseThrow(()-> new CustomException(NOT_FOUND_COMMENT));
		updateComment.decreaseLikes();

		commentRepository.save(updateComment);
	}


	@KafkaListener(topics = KafkaTopic.DELETE_COMMENT)
	public void deleteComment(String message) throws JsonProcessingException {
		log.info("[Kafka message]: " + message);

		Long commentId = mapper.readValue(message, Long.class);

		Comment storedComment = commentRepository.findByCommentId(commentId)
			.orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
		commentRepository.delete(storedComment);
	}

	private SavedCommentMessage readCommentByJson(String json) throws JsonProcessingException {
		log.info("[Kafka message]: " + json);
		return mapper.readValue(json, SavedCommentMessage.class);
	}

}
