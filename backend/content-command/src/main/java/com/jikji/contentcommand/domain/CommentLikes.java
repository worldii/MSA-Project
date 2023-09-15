package com.jikji.contentcommand.domain;

import javax.persistence.*;
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


	@JoinColumn(name = "comment_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Comment comment;

	@Builder
	public CommentLikes(final Long userId, final Comment comment) {
		this.comment = comment;
		this.userId = userId;
		this.comment.addCommentLikes(this);
	}
}
