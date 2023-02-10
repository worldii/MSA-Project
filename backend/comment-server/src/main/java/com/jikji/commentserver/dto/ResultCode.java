package com.jikji.commentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
	// COMMENT
	CREATE_COMMENT_SUCCESS(200, "C001", "댓글 업로드에 성공하였습니다."),
	DELETE_COMMENT_SUCCESS(200, "C002", "댓글 삭제에 성공하였습니다."),
	GET_COMMENT_PAGE_SUCCESS(200, "C003", "댓글 목록 페이지 조회에 성공하였습니다."),
	UPDATE_COMMENT_SUCCESS(200, "C004", "댓글 수정에 성공하였습니다."),
	LIKE_COMMENT_SUCCESS(200, "C005", "댓글 좋아요에 성공하였습니다."),
	UNLIKE_COMMENT_SUCCESS(200, "C006", "댓글 좋아요 해제에 성공하였습니다."),
	MENTION_COMMENT_SUCCESS(200, "C007", "댓글 멘션에 성공하였습니다."),
	GET_COMMENT_LIKES_SUCCESS(200, "C008", "댓글에 좋아요한 회원 목록 페이지 조회에 성공하였습니다."),
	GET_COMMENT_IS_LIKED_SUCCESS (200,"C009","특정 유저의 댓글 좋아요 여부 조회에 성공하였습니다." );

	private final int status;
	private final String code;
	private final String message;

}

