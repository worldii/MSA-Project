package com.jikji.contentquery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContentResponse {

	private Long id;
	private String content;
	private String url;
	private Integer[] createdAt;
	private boolean isRead;

	@Builder
	public ContentResponse(Long id, String content, String url, LocalDateTime createdAt, boolean read) {
		this.id = id;
		this.content = content;
		this.url = url;
		this.createdAt = LocalDateTimeToArray.convert(createdAt);
		this.isRead = read;
	}

	public static ContentResponse from(Content notification) {
		return ContentResponse.builder()
			.id(notification.getId())
			.content(notification.getContent())
			.url(notification.getUrl())
			.createdAt(notification.getCreatedAt())
			.read(notification.isRead())
			.build();
	}
}
