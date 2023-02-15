package com.jikji.commentserver.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jikji.commentserver.domain.Comment;
import com.jikji.commentserver.domain.CommentLikes;
import com.jikji.commentserver.domain.User;
import com.jikji.commentserver.dto.CommentCreateDto;
import com.jikji.commentserver.dto.CommentDto;
import com.jikji.commentserver.dto.CommentResponseData;
import com.jikji.commentserver.exception.CustomException;
import com.jikji.commentserver.exception.ErrorCode;
import com.jikji.commentserver.repository.CommentLikesRepository;
import com.jikji.commentserver.repository.CommentRepository;
import com.jikji.commentserver.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final CommentLikesRepository commentLikesRepository;
	private final CommentMentionService commentMentionService;

	@Transactional(readOnly = true)
	public List<Comment> findAllComments(Long postId) {
		return commentRepository.findAllByPostId(postId);
	}

	@Transactional
	public CommentResponseData createComment(Long postId, CommentCreateDto commentCreateDto) {
		// Refactoring 할 것.
		// 1. Post Service 에서 postId가 있는 지 확인. Exception 처리도 해줘야 함.(Post 서비스)
		// 2. User Service 에서 로그인 한 user server 있는 지 확인.  Exception 처리도 해줘야 함. (User 서비스)
		// 3. Notification Service 에 보냄.

		log.info("comment create");
		// User tempUser = userRepository.findById(commentCreateDto.getUserId())
		// 	.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

		Comment comment = Comment.builder()
			.userId(commentCreateDto.getUserId())
			.description(commentCreateDto.getDescription())
			.postId(postId).build();
		commentRepository.save(comment);

		log.info("Mention Someone");
		commentMentionService.mentionMember(tempUser, comment);

		CommentResponseData commentResponseData = CommentResponseData.builder()
			.id(comment.getId())
			.createdAt(comment.getCreatedAt())
			.from(comment.getUser())
			.description(comment.getDescription())
			.build();
		return commentResponseData;
	}

	@Transactional
	public void deleteComment(Long commentId) {
		// Refactoring
		// delete하는 comment의 User와 login 한 User 가 같아야 함.-> Error 처리. (User Server)
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

		this.deleteCommentLikesAll(comment);
		commentMentionService.deleteMentionAll(comment);
		commentRepository.delete(comment);
	}

	@Transactional
	public void updateComment(Long commentId, CommentDto commentDto) {
		// Refactoring
		// 댓글 쓰려는 유저가 맞지 않을 때 -> from user server.
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));
		comment.update(commentDto.getDescription());
	}

	@Transactional
	public void addCommentLikes(Long commentId, Long userId) {
		// Refactoring 필요
		// 1. 현재 로그인 한 유저를 가져옴 (from UserService) (Refactoring 해야 함.)
		// 2. Notification 서비스 보내기. ( Notification refactoring)

		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));
		// User tempUser = userRepository.findById(userId)
		// 	.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

		if (commentLikesRepository.findByUserAndComment(tempUser, comment).isPresent()) {
			throw new CustomException(ErrorCode.COMMENT_ALREADY_EXIST);
		}

		comment.increaseLikes();
		CommentLikes commentLikes = CommentLikes.builder().comment.user(tempUser).build();
		commentLikesRepository.save(commentLikes);
	}

	@Transactional
	public void deleteCommentLikes(Long commentId, Long userId) {
		// for Refactoring
		// 1. 현재 로그인 한 유저를 가져옴 (from UserService)
		// 2. Notification 보내기 ( to Notification service) -> Refactoring

		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

		User tempUser = userRepository.findById(userId)
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

		CommentLikes commentLikes = commentLikesRepository.findByUserAndComment(tempUser, comment)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

		comment.decreaseLikes();
		commentLikesRepository.delete(commentLikes);
	}

	@Transactional
	public List<CommentLikes> getCommentLikesList(long commentId) {
		List<CommentLikes> allByCommentId = commentLikesRepository.findAllByCommentId(commentId);
		log.info("좋아요 리스트 " + allByCommentId);
		return allByCommentId;
	}

	@Transactional
	public boolean checkCommentIsLikedByUser(long commentId, long userId) {
		boolean present = commentLikesRepository.findByUserIdAndCommentId(userId, commentId).isPresent();
		log.info("좋아요 여부 " + present);
		return present;
	}

	private void deleteCommentLikesAll(Comment comment) {
		List<CommentLikes> allByComment = commentLikesRepository.findAllByComment(comment);
		commentLikesRepository.deleteAll(allByComment);
	}
}
