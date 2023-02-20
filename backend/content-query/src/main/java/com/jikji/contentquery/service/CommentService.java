package com.jikji.contentquery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.CommentLikes;
import com.jikji.contentquery.repository.CommentLikesRepository;
import com.jikji.contentquery.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentLikesRepository commentLikesRepository;

	@Transactional(readOnly = true)
	public List<Comment> findAllComments(Long postId) {
		return commentRepository.findAllByPostId(postId);
	}

	@Transactional
	public List<CommentLikes> getCommentLikesList(Long commentId) {
		List<CommentLikes> allByCommentId = commentLikesRepository.findAllByCommentId(commentId);
		log.info("좋아요 리스트 " + allByCommentId);
		return allByCommentId;
	}

	@Transactional
	public boolean checkCommentIsLikedByUser(Long commentId, Long userId) {
		boolean present = commentLikesRepository.findByUserIdAndCommentId(userId, commentId).isPresent();
		log.info("좋아요 여부 " + present);
		return present;
	}
}
