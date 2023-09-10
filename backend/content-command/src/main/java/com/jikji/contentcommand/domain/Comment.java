package com.jikji.contentcommand.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;

	@Column(nullable = false, updatable = false, name = "user_id")
	private Long userId;
	private String userName;
	private String profileUrl;

	@NotNull
	@Column(name = "post_id")
	private Long postId;

	@Column(name = "created_at")
	private String createdAt ;

	@Column(nullable = false, length = 3000)
	private String description;

	private Long likes = 0L;

	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CommentLikes> commentLikes = new ArrayList<>();

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	@Builder
	public Comment(final Long userId, final String userName, final String profileUrl,
				   final String description, final Long postId
	) {
		this.userId = userId;
		this.userName = userName;
		this.profileUrl = profileUrl;
		this.description = description;
		this.postId = postId;
	}

	public void updateDescription(String description) {
		this.description = description;
	}

	public void addCommentLikes(CommentLikes commentLikes) {
		this.commentLikes.add(commentLikes);
	}
}

