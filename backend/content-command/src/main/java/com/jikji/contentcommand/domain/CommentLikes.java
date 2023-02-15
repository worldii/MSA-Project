package com.jikji.contentcommand.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment_likes", indexes = {
		@Index(name = "user_comment_ui", columnList = "user_id, comment_id")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikes {

	@Id
	@GeneratedValue
	@Column(name = "comment_likes_id")
	private long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "comment_id", nullable = false)
	private Long commentId;

	@Builder
	public CommentLikes(Long userId, Long commentId) {
		this.userId = userId;
		this.commentId = commentId;
	}
}
