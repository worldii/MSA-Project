package com.jikji.contentquery.dto.response;

import java.time.LocalDateTime;
import com.jikji.contentquery.domain.User;

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
