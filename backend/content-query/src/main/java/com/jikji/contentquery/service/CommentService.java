package com.jikji.contentquery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.CommentLikes;
import com.jikji.contentquery.repository.CommentLikesRepository;
import com.jikji.contentquery.repository.CommentRepository;
import com.jikji.contentquery.repository.UserRepository;

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

}
