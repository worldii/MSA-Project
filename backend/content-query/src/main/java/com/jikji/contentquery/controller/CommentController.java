package com.jikji.contentquery.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.CommentLikes;
import com.jikji.contentquery.dto.request.CommentDto;
import com.jikji.contentquery.dto.request.CommentLikesDto;
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
		List<CommentDto> collect = allComments.stream()
			.map(comment -> new CommentDto(comment.getId(), comment.getUser().getUserName(),
				comment.getUser().getProfilePicture(), comment.getDescription(),
				comment.getLikes()))
			.collect(
				Collectors.toList());
		return ResponseEntity.ok(new ResultResponse(ResultCode.GET_COMMENT_PAGE_SUCCESS, collect));
	}

	@GetMapping("/comments/like/{commentId}")
	ResponseEntity<ResultResponse> getCommentLikesList(@PathVariable("commentId") long commentId) {
		List<CommentLikes> commentLikesList = commentService.getCommentLikesList(commentId);
		List<CommentLikesDto> collect = commentLikesList.stream()
			.map(comment -> new CommentLikesDto(comment.getUser().getUserName(), comment.getUser().getProfilePicture(),
				comment.getUser().getFullName()))
			.collect(
				Collectors.toList());

		return ResponseEntity.ok(new ResultResponse(ResultCode.GET_COMMENT_LIKES_SUCCESS, collect));
	}

	@GetMapping("/comments/isLiked/{commentId}/{userId}")
	ResponseEntity<ResultResponse> getCommentisLikedByUser(@PathVariable("commentId") long commentId,
		@PathVariable("userId") long userId) {
		boolean result = commentService.checkCommentIsLikedByUser(commentId, userId);
		return ResponseEntity.ok(new ResultResponse(ResultCode.GET_COMMENT_IS_LIKED_SUCCESS, result));
	}
}
