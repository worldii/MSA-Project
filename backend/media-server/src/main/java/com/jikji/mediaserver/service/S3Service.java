package com.jikji.mediaserver.service;

import java.io.IOException;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.jikji.mediaserver.dto.request.MediaRequestDto;
import com.jikji.mediaserver.util.MediaUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	private final AmazonS3 amazonS3;

	@Transactional
	public String uploadMediaToS3(MediaRequestDto mediaRequestDto, Long userId) throws IOException {
		long now = (new Date()).getTime();
		String fileName = now + mediaRequestDto.getFile().getOriginalFilename();
		log.info("File name: " + fileName);

		String contentType = MediaUtil.findContentType(mediaRequestDto.getFile().getContentType());
		String folder = MediaUtil.findFolder(fileName, "" + userId, contentType);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);

		amazonS3.putObject(
			new PutObjectRequest(bucket, folder, mediaRequestDto.getFile().getInputStream(),
				metadata).withCannedAcl(
				CannedAccessControlList.PublicRead));

		return amazonS3.getUrl(bucket, fileName).toString();
	}
}


