package com.jikji.mediaserver.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jikji.mediaserver.dto.request.MediaRequest;
import com.jikji.mediaserver.dto.response.MediaResponse;
import com.jikji.mediaserver.dto.response.ResultCode;
import com.jikji.mediaserver.dto.response.ResultResponse;
import com.jikji.mediaserver.service.MediaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MediaController {
	private final MediaService mediaService;

	@PostMapping(value = "/media", consumes = {"multipart/form-data"})
	public ResponseEntity<ResultResponse> uploadMedia(
		@ModelAttribute final MediaRequest mediaRequest
	) throws IOException {
		MediaResponse mediaResponse = mediaService.save(mediaRequest);
		return ResponseEntity.ok(new ResultResponse(ResultCode.MEDIA_UPLOAD_SUCCESS, mediaResponse));
	}
}
