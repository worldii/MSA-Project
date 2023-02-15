package com.jikji.contentcommand.dto.message;

import java.util.List;

import com.jikji.contentcommand.domain.Comment;
import com.jikji.contentcommand.domain.Content;
import com.jikji.contentcommand.domain.ImageUrl;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentKafkaMessage {
	private Long commentId;
	private Long userId;
	private String userName;
	private String profileUrl;
	private int likes;
	private String createdAt;

	public CommentKafkaMessage(Comment comment) {
		this.commentId = comment.getId();
		this.userId = comment.getUserId();
		this.userName = comment.getUserName();
		this.profileUrl = comment.getProfileUrl();
		this.createdAt = comment.getCreatedAt();
		this.likes = comment.getLikes();
	}
}


