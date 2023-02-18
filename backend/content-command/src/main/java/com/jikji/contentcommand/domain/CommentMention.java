package com.jikji.contentcommand.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;
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

	@Column(name = "sender_id", nullable = false)
	private Long senderId;
	@Column(name = "receiver_id", nullable = false)
	private Long receiverId;
	@Column(name = "comment_id", nullable = false)
	private Long commentId;

	@Column(name = "created_at")
	private String createdAt ;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	@Builder
	public CommentMention(Long senderId, Long receiverId, Long commentId) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.commentId = commentId;
	}
}
