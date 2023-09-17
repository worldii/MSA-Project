package com.jikji.contentcommand.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jikji.contentcommand.dto.request.CommentCreateRequest;
import com.jikji.contentcommand.dto.request.CommentRequest;
import com.jikji.contentcommand.dto.response.ResultCode;
import com.jikji.contentcommand.dto.response.ResultResponse;
import com.jikji.contentcommand.service.CommentService;

import lombok.RequiredArgsConstructor;

import static com.jikji.contentcommand.dto.response.ResultCode.*;

@RestController
@RequiredArgsConstructor
public class CommentCommandController {
    private final CommentService commentService;

    @PostMapping(value = "/comments/{postId}")
    public ResponseEntity<ResultResponse> createComment(
        @PathVariable final Long postId,
        @RequestBody @Valid final CommentCreateRequest req
    ) {
        return ResponseEntity.ok(
            new ResultResponse(CREATE_COMMENT_SUCCESS, commentService.createComment(postId, req))
        );
    }

    @DeleteMapping("/comments/{commentId}")
    ResponseEntity<ResultResponse> deleteComment(@PathVariable("commentId") final Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(ResultResponse.from(ResultCode.DELETE_COMMENT_SUCCESS));
    }

    @PutMapping("/comments/{commentId}")
    ResponseEntity<ResultResponse> updateComment(
        @PathVariable("commentId") final Long commentId,
        @RequestBody @Valid final CommentRequest commentRequest
    ) {
        commentService.updateComment(commentId, commentRequest);
        return ResponseEntity.ok(ResultResponse.from(UPDATE_COMMENT_SUCCESS));
    }

    @PostMapping("/comments/like/{commentId}/{userId}")
    ResponseEntity<ResultResponse> addCommentLikes(
        @PathVariable("commentId") final Long commentId,
        @PathVariable("userId") final Long userId
    ) {
        commentService.addCommentLikes(commentId, userId);
        return ResponseEntity.ok(ResultResponse.from(LIKE_COMMENT_SUCCESS));
    }

    @PostMapping("comments/unlike/{commentId}/{userId}")
    ResponseEntity<ResultResponse> deleteCommentLikes(
        @PathVariable("commentId") final Long commentId,
        @PathVariable("userId") final Long userId
    ) {
        commentService.deleteCommentLikes(commentId, userId);
        return ResponseEntity.ok(ResultResponse.from(UNLIKE_COMMENT_SUCCESS));
    }
}
