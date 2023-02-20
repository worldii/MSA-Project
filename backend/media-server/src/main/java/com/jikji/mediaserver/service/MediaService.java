package com.jikji.mediaserver.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.jikji.mediaserver.dto.request.MediaRequestDto;
import com.jikji.mediaserver.dto.response.MediaResponseData;
import com.jikji.mediaserver.model.Media;
import com.jikji.mediaserver.model.MediaType;
import com.jikji.mediaserver.repository.MediaRepository;
import com.jikji.mediaserver.util.MediaUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaService {
	private final MediaRepository mediaRepository;
	private final S3Service s3Service;

	@Transactional
	public MediaResponseData save(MediaRequestDto mediaRequestDto) throws IOException {
		String url = s3Service.uploadMediaToS3(mediaRequestDto, mediaRequestDto.getUserId());

		String contentType = MediaUtil.findContentType(mediaRequestDto.getFile().getContentType());
		log.info("Content Type change to Enum Type " + MediaType.valueOf(contentType));

		Media media = Media.builder().url(url)
			.mediaType(MediaType.valueOf(contentType))
			.userId(mediaRequestDto.getUserId()).build();
		mediaRepository.save(media);

		MediaResponseData mediaResponseData = MediaResponseData.builder()
			.mediaType(media.getMediaType())
			.url(media.getUrl())
			.build();

		return mediaResponseData;
	}
}
