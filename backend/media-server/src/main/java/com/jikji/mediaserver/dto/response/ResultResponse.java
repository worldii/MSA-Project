package com.jikji.mediaserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse<T> {
	private int status;
	private String code;
	private String message;
	private T data;

	public ResultResponse(ResultCode resultCode, T data) {
		this.status = resultCode.getStatus();
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.data = data;
	}
}
