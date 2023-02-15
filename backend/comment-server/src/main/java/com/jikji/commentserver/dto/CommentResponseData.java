package com.jikji.commentserver.dto;

import java.time.LocalDateTime;

import com.jikji.commentserver.domain.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponseData {
	private Long id;
	private String fromUserId;
	private String description;
	private LocalDateTime createdAt;
}
