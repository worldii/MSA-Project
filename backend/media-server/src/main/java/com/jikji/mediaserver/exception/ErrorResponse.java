package com.jikji.mediaserver.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	private int status;
	private String code;
	private String message;

	public static ResponseEntity<ErrorResponse> toResponseEntity(final ErrorCode errorcode) {
		return ResponseEntity.status(HttpStatus.valueOf(errorcode.getStatus()))
			.body(ErrorResponse.builder()
				.status(errorcode.getStatus())
				.code(errorcode.getCode())
				.message(errorcode.getMessage())
				.build()
			);
	}
}
