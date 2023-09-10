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

import com.jikji.mediaserver.dto.request.MediaRequest;
import com.jikji.mediaserver.util.MediaUtil;

@Service
public class S3Service {

	private final String bucket;
	private final AmazonS3 amazonS3;

	public S3Service(@Value("${cloud.aws.s3.bucket}")  final String bucket, final AmazonS3 amazonS3) {
		this.bucket = bucket;
		this.amazonS3 = amazonS3;
	}

	@Transactional
	public String uploadMediaToS3(final MediaRequest mediaRequest, final Long userId) throws IOException {
		String fileName = new Date().getTime() + mediaRequest.getFile().getOriginalFilename();
		String contentType = MediaUtil.findContentType(mediaRequest.getFile().getContentType());
		String folder = MediaUtil.findFolder(fileName, "" + userId, contentType);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);

		amazonS3.putObject(
			new PutObjectRequest(bucket, folder, mediaRequest.getFile().getInputStream(), metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead)
		);

		return amazonS3.getUrl(bucket, fileName).toString();
	}
}


