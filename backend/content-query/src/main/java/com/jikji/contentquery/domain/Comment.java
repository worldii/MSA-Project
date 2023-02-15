package com.jikji.contentquery.domain;
import java.util.List;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Document(collection = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {

	@Id
	@Field(value = "_id", targetType = FieldType.OBJECT_ID)
	private String id;

	@Indexed(unique = true)
	private Long commentId;

	private Long userId;

	private String userName;
	private String profileUrl;

	private Long postId;

	// @CreationTimestamp
	// @Column(name = "created_at")
	private String createdAt;
	//@Column(nullable = false, length = 3000)
	private String description;

	private int likes;

	@Builder
	public Comment(Long userId, String userName, String profileUrl, String description, Long postId, int likes, List<CommentLikes> commentLikes) {
		this.userId = userId;
		this.userName = userName;
		this.profileUrl = profileUrl;
		this.description = description;
		this.postId = postId;
		this.likes = likes;
	}

	public void update(String description) {
		this.description = description;
	}
	public void increaseLikes() {
		this.likes = this.likes + 1;
	}
	public void decreaseLikes() {
		this.likes = this.likes - 1;
	}
}

