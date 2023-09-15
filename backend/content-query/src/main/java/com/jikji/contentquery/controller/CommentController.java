package com.jikji.contentquery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.CommentLikes;
import com.jikji.contentquery.dto.response.ResultCode;
import com.jikji.contentquery.dto.response.ResultResponse;
import com.jikji.contentquery.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
	private final CommentService commentService;

	@GetMapping("/comments/{postId}")
	public ResponseEntity<ResultResponse> getComments(@PathVariable Long postId) {
		List<Comment> allComments = commentService.findAllComments(postId);
		return ResponseEntity.ok(new ResultResponse(ResultCode.GET_COMMENT_PAGE_SUCCESS, allComments));
	}

	@GetMapping("/comments/like/{commentId}")
	ResponseEntity<ResultResponse> getCommentLikesList(@PathVariable("commentId") Long commentId
	) {
		List<CommentLikes> commentLikesList = commentService.getCommentLikesList(commentId);
		return ResponseEntity.ok(new ResultResponse(ResultCode.GET_COMMENT_LIKES_SUCCESS, commentLikesList));
	}

	@GetMapping("/comments/isLiked/{commentId}/{userId}")
	ResponseEntity<ResultResponse> getCommentIsLikedByUser(
		@PathVariable("commentId") long commentId,
		@PathVariable("userId") long userId
	) {
		boolean result = commentService.checkCommentIsLikedByUser(commentId, userId);
		return ResponseEntity.ok(new ResultResponse(ResultCode.GET_COMMENT_IS_LIKED_SUCCESS, result));
	}
}
