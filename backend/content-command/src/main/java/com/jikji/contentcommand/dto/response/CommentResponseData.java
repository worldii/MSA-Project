package com.jikji.contentcommand.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponseData {
	private Long id;
	private Long userId;
	private String description;
	private LocalDateTime createdAt;
}
