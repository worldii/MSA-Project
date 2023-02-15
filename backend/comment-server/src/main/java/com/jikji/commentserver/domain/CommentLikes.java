package com.jikji.commentserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikes {

	@Id
	@GeneratedValue
	@Column(name = "comment_likes_id")
	private long id;

	private Long userId;

	private Long commentId;

	@Builder
	public CommentLikes(Long userId, Long commentId) {
		this.userId = userId;
		this.commentId = commentId;
	}
}
