package com.jikji.mediaserver.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jikji.mediaserver.dto.request.MediaRequestDto;
import com.jikji.mediaserver.dto.response.MediaResponseData;
import com.jikji.mediaserver.dto.response.ResultCode;
import com.jikji.mediaserver.dto.response.ResultResponse;
import com.jikji.mediaserver.service.MediaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MediaController {
	private final MediaService mediaService;

	@PostMapping(value = "/media", consumes = {"multipart/form-data"})
	public ResponseEntity<ResultResponse> uploadMedia(@ModelAttribute MediaRequestDto mediaRequestDto) throws IOException {
		log.info(mediaRequestDto.getFile().getOriginalFilename());
		MediaResponseData mediaResponseData = mediaService.save(mediaRequestDto);
		return ResponseEntity.ok(new ResultResponse(ResultCode.MEDIA_UPLOAD_SUCCESS, mediaResponseData));
	}
}
