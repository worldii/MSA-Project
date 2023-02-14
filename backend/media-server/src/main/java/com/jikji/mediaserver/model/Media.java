package com.jikji.mediaserver.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Media {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "media_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "media_type")
	private MediaType mediaType;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	private String url;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "user_id")
	// private User user;
	private Long userId;
	private String userName;

	@Builder
	public Media(String url, Long userId, MediaType mediaType, String userName) {
		this.mediaType = mediaType;
		this.url = url;
		//this.user = user;
		this.userId = userId;
		this.userName = userName;
	}

}
