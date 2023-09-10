package com.jikji.contentcommand.dto.message;

import com.jikji.contentcommand.domain.Comment;
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
	private Long id;
	private Comment comment;
	private Long userId;

	public CommentLikeKafkaMessage(CommentLikes commentLikes) {
		this.id = commentLikes.getId();
		this.comment= commentLikes.getComment();
		this.userId = commentLikes.getUserId();
	}

}
