package com.jikji.contentcommand.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "user_id")
	private User user;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@NotNull
	private String description;

	@NotNull
	private Long postId;
	private int likes;

	@OneToMany(mappedBy = "comment")
	private List<CommentLikes> commentLikes = new ArrayList<>();

	@Builder
	public Comment(User user, String description, Long postId, int likes, List<CommentLikes> commentLikes) {
		this.user = user;
		this.description = description;
		this.postId = postId;
		this.likes = likes;
		this.commentLikes = commentLikes;
	}

	public void last_update(String description) {
		this.description = description;
	}
	public void increaseLikes() {
		this.likes = this.likes + 1;
	}
	public void decreaseLikes() {
		this.likes = this.likes - 1;
	}
}

