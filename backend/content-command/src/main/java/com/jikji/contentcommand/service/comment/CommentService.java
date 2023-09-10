package com.jikji.contentcommand.service.comment;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jikji.contentcommand.domain.Comment;
import com.jikji.contentcommand.domain.CommentLikes;
import com.jikji.contentcommand.dto.message.CommentKafkaMessage;
import com.jikji.contentcommand.dto.message.CommentLikeKafkaMessage;
import com.jikji.contentcommand.dto.request.CommentCreateRequest;
import com.jikji.contentcommand.dto.request.CommentRequest;
import com.jikji.contentcommand.dto.response.CommentResponse;
import com.jikji.contentcommand.exception.CustomException;
import com.jikji.contentcommand.exception.ErrorCode;
import com.jikji.contentcommand.repository.CommentLikesRepository;
import com.jikji.contentcommand.repository.CommentRepository;
import com.jikji.contentcommand.util.KafkaTopic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jikji.contentcommand.exception.ErrorCode.NOT_FOUND_COMMENT;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final CommentLikesRepository commentLikesRepository;
	private final CommentMentionService commentMentionService;
	private final KafkaTemplate<String, String> kafkaTemplate;

	@Transactional
	public CommentResponse createComment(Long postId, CommentCreateRequest req) {
		Comment comment = Comment.builder()
			.userId(req.getUserId())
			.userName(req.getUserName())
			.profileUrl(req.getProfileUrl())
			.description(req.getDescription())
			.postId(postId)
			.build();
		commentRepository.save(comment);

		sendMessage(comment, KafkaTopic.ADD_COMMENT);
		commentMentionService.mentionMember(req.getUserId(), req.getDescription());

		return CommentResponse.from(comment);
	}

	@Transactional
	public void deleteComment(Long commentId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));

		commentMentionService.deleteMentionAll(commentId);
		commentRepository.delete(comment);

		kafkaTemplate.send(KafkaTopic.DELETE_COMMENT, String.valueOf(commentId));
	}

	@Transactional
	public void updateComment(Long commentId, CommentRequest commentRequest) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));

		comment.updateDescription(commentRequest.getDescription());
	}

	@Transactional
	public void addCommentLikes(Long commentId, Long userId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));

		if (commentLikesRepository.findByComment(comment).isPresent()) {
			throw new CustomException(ErrorCode.COMMENT_ALREADY_EXIST);
		}

		CommentLikes commentLikes = CommentLikes
			.builder()
			.comment(comment)
			.userId(userId)
			.build();
		commentLikesRepository.save(commentLikes);

		sendMessage(commentLikes, KafkaTopic.ADD_COMMENT_LIKE);
		sendMessage(comment, KafkaTopic.INCREASE_COMMENT_LIKES);
	}

	@Transactional
	public void deleteCommentLikes(Long commentId, Long userId) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));

		CommentLikes commentLikes = commentLikesRepository
			.findByComment(comment)
			.orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
		commentLikesRepository.delete(commentLikes);

		sendMessage(commentLikes, KafkaTopic.DELETE_COMMENT_LIKE);
		sendMessage(comment, KafkaTopic.DECREASE_COMMENT_LIKES);
	}


	private void sendMessage(Comment comment, String topic) {
		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String data = null;
		try {
			CommentKafkaMessage message = new CommentKafkaMessage(comment);
			data = writer.writeValueAsString(message);
			kafkaTemplate.send(topic, data);

		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		log.info("comment kafka send - " + data);
	}

	private void sendMessage(CommentLikes commentLikes, String topic) {
		ObjectWriter writer = new ObjectMapper().writer();
		String data = null;

		try {
			CommentLikeKafkaMessage message = CommentLikeKafkaMessage.builder()
				.comment(commentLikes.getComment())
				.id(commentLikes.getId())
				.userId(commentLikes.getUserId())
				.build();

			data = writer.writeValueAsString(message);
			kafkaTemplate.send(topic, data);

		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		log.info("commentlikes kafka send - " + data);
	}
}
