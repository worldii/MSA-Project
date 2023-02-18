package com.jikji.contentcommand.dto.message;

import com.jikji.contentcommand.domain.CommentLikes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommentLikeKafkaMessage {
	private Long commentLikeId;
	private Long commentId;
	private Long userId;

	public CommentLikeKafkaMessage(CommentLikes commentLikes) {
		this.commentLikeId = commentLikes.getId();
		this.commentId = commentLikes.getCommentId();
		this.userId = commentLikes.getUserId();
	}
}
