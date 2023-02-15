package com.jikji.commentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
	private Long id;
	private String userName;
	private String description;
	private Long likes;
}
