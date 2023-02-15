package com.jikji.contentquery.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "comment_mention")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentMention {
	@Id
	@Field(value = "_id", targetType = FieldType.OBJECT_ID)
	private String id;

	private Long bookmarkId;
	private Long senderId;
	private Long receiverId;
	private Long commentId;
	private String createdAt ;

	@Builder
	public CommentMention(Long bookmarkId ,Long senderId, Long receiverId, Long commentId) {
		this.bookmarkId = bookmarkId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.commentId = commentId;
	}
}
