package com.jikji.contentcommand.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class CommentLikesDto {

	String username;
	String profileUrl;
	String fullName;
}
