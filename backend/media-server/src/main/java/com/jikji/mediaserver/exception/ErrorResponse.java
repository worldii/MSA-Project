package com.example.mediaserver.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Data
@AllArgsConstructor
public class ErrorResponse {
	private int status;
	private String code;
	private String message;

	public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorcode) {
		return ResponseEntity.status(HttpStatus.valueOf(errorcode.getStatus()))
			.body(ErrorResponse.builder()
				.status(errorcode.getStatus())
				.code(errorcode.getCode())
				.message(errorcode.getMessage())
				.build()
			);
	}
}