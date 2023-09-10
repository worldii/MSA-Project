package com.jikji.mediaserver.util;

import com.jikji.mediaserver.exception.CustomException;

import lombok.extern.slf4j.Slf4j;
import java.util.Set;

import static com.jikji.mediaserver.exception.ErrorCode.FILE_CONVERT_FAIL;

@Slf4j
public class MediaUtil {
	private static final Set<String> UPPER_CASES = Set.of("IMAGE", "VIDEO");

	public static String findContentType(final String contentType) {
		String[] mediaContentType = contentType.split("/");

		if (mediaContentType.length == 0)
			throw new CustomException(FILE_CONVERT_FAIL);

		if (!UPPER_CASES.contains(mediaContentType[0].toUpperCase())) {
			throw new CustomException(FILE_CONVERT_FAIL);
		}

		return mediaContentType[0].toUpperCase();
	}

	public static String findFolder(final String filename, final String userName, final String contentType) {
		return contentType + "/" + userName + "/" + contentType + "/" + filename;
	}
}
