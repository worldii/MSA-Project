package com.jikji.mediaserver.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
	private ErrorCode errorcode;
}
