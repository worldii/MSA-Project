package com.jikji.commentserver.dto;

import java.time.LocalDateTime;

import com.example.comment.domain.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponseData {
	private Long id;
	private User from;
	private String description;
	private LocalDateTime createdAt;
}
