package com.jikji.contentcommand.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jikji.contentcommand.dto.request.CommentCreateDto;
import com.jikji.contentcommand.dto.request.CommentDto;
import com.jikji.contentcommand.dto.response.CommentResponseData;
import com.jikji.contentcommand.dto.response.ResultCode;
import com.jikji.contentcommand.dto.response.ResultResponse;
import com.jikji.contentcommand.service.comment.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentCommandController {
	private final CommentService commentService;

	@PostMapping(value = "/comments/{postId}")
	public ResponseEntity<ResultResponse> createComment(@PathVariable("postId") Long postId,
		@RequestBody @Valid CommentCreateDto commentCreateDto) {
		CommentResponseData responseData = commentService.createComment(postId, commentCreateDto);
		return ResponseEntity.ok(new ResultResponse(ResultCode.CREATE_COMMENT_SUCCESS, responseData));
	}

	@DeleteMapping("/comments/{commentId}")
	ResponseEntity<ResultResponse> deleteComment(@PathVariable("commentId") Long commentId) {
		commentService.deleteComment(commentId);
		return ResponseEntity.ok(new ResultResponse(ResultCode.DELETE_COMMENT_SUCCESS, ""));
	}

	@PutMapping("/comments/{commentId}")
	ResponseEntity<ResultResponse> updateComment(@PathVariable("commentId") Long commentId,
		@RequestBody @Valid CommentDto commentDto) {
		commentService.updateComment(commentId, commentDto);
		return ResponseEntity.ok(new ResultResponse(ResultCode.UPDATE_COMMENT_SUCCESS, ""));
	}

	@PostMapping("/comments/like/{commentId}/{userId}")
	ResponseEntity<ResultResponse> addCommentLikes(@PathVariable("commentId") Long commentId,
		@PathVariable("userId") Long userId) {
		commentService.addCommentLikes(commentId, userId);
		return ResponseEntity.ok(new ResultResponse(ResultCode.LIKE_COMMENT_SUCCESS, ""));
	}

	@PostMapping("comments/unlike/{commentId}/{userId}")
	ResponseEntity<ResultResponse> deleteCommentLikes(@PathVariable("commentId") Long commentId,
		@PathVariable("userId") Long userId) {
		commentService.deleteCommentLikes(commentId, userId);
		return ResponseEntity.ok(new ResultResponse(ResultCode.UNLIKE_COMMENT_SUCCESS, ""));
	}
}
