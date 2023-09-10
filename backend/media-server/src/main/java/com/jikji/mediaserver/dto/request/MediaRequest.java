package com.jikji.mediaserver.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaRequest {
	private Long userId;
	private MultipartFile file;
}
