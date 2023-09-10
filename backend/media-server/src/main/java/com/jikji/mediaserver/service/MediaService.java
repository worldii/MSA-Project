package com.jikji.mediaserver.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.jikji.mediaserver.dto.request.MediaRequest;
import com.jikji.mediaserver.dto.response.MediaResponse;
import com.jikji.mediaserver.model.Media;
import com.jikji.mediaserver.model.MediaType;
import com.jikji.mediaserver.repository.MediaRepository;
import com.jikji.mediaserver.util.MediaUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaService {
	private final MediaRepository mediaRepository;
	private final S3Service s3Service;

	@Transactional
	public MediaResponse save(final MediaRequest mediaRequest) throws IOException {
		String url = s3Service.uploadMediaToS3(mediaRequest, mediaRequest.getUserId());
		String contentType = MediaUtil.findContentType(mediaRequest.getFile().getContentType());

		Media media = Media.builder().url(url)
			.mediaType(MediaType.valueOf(contentType))
			.userId(mediaRequest.getUserId()).build();

		return MediaResponse.from(mediaRepository.save(media));
	}
}
