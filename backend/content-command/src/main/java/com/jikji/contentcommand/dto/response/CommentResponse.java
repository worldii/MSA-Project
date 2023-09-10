package com.jikji.contentcommand.dto.response;

import com.jikji.contentcommand.domain.Comment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponse {
	private Long id;
	private Long userId;
	private String description;
	private String createdAt;

    public static CommentResponse from(Comment comment) {
		return CommentResponse.builder()
				.id(comment.getId())
				.userId(comment.getUserId())
				.description(comment.getDescription())
				.createdAt(comment.getCreatedAt())
				.build();
	}
}
