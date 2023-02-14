package com.jikji.contentquery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
	private Long id;
	private String userName;
	private String ProfileUrl;
	private String description;
	private int likes;
}
