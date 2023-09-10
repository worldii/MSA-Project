package com.jikji.mediaserver.dto.response;

import com.jikji.mediaserver.model.Media;
import com.jikji.mediaserver.model.MediaType;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaResponse {
	private String url;
	private MediaType mediaType;

	public static MediaResponse from(final Media media) {
		return new MediaResponse(media.getUrl(), media.getMediaType());
	}
}
