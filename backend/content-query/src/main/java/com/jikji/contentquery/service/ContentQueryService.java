package com.jikji.contentquery.service;

import com.jikji.contentquery.domain.Content;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ContentQueryService {

	Content findByContentId(Long contentId);

	List<Content> findByUserId(Long userId);

	Page<Content> findByContentIdWithSize(Long userId, Long contentId, Integer size);

}
