package com.jikji.contentcommand.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

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

	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@NotNull
	//	@JoinColumn(name = "user_id")
	//private User user;

	@Column(nullable = false, updatable = false, name = "user_id")
	private Long userId;

	private String userName;
	private String profileUrl;

	@NotNull
	private Long postId;

	@Column(name = "created_at")
	private String createdAt ;

	@Column(nullable = false, length = 3000)
	private String description;

	private int likes;

//	@OneToMany(mappedBy = "comment")
//	private List<CommentLikes> commentLikes = new ArrayList<>();

	@PrePersist
	public void prePersist() {
		likes = 0;
		createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

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

