package com.jikji.contentcommand.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment_mention", indexes = {
		@Index(name = "user_comment_mention_ui", columnList = "sender_id, receiver_id, comment_id")
})
public class CommentMention {
	@Id
	@GeneratedValue
	@Column(name = "comment_mention_id")
	private Long id;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "sender_id")
//	private User sender;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "receiver_Id")
//	private User receiver;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "comment_id")
//	private Comment comment;

	@Column(name = "sender_id", nullable = false)
	private Long senderId;
	@Column(name = "receiver_id", nullable = false)
	private Long receiverId;
	@Column(name = "comment_id", nullable = false)
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
