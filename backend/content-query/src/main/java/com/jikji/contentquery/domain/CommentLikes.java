package com.jikji.contentquery.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document("comment_likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikes {

	@Id
	@Field(value = "_id", targetType = FieldType.OBJECT_ID)
	private String id;

	@Indexed(unique = true)
	private Long commentLikeId;
	private Long userId;
	private Long commentId;

	@Builder
	public CommentLikes(Long commentLikesId, Long userId, Long commentId) {
		this.commentLikeId = commentLikesId;
		this.userId = userId;
		this.commentId = commentId;
	}
}
