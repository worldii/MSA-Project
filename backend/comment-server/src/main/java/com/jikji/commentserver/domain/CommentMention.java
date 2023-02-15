package com.jikji.commentserver.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentMention {
	@Id
	@GeneratedValue
	@Column(name = "comment_mention_id")
	private Long id;

	private Long senderId;
	private Long receiverId;
	private Long commentId;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@Builder
	public CommentMention(Long senderId, Long receiverId, Long commentId) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.commentId = commentId;
	}
}
