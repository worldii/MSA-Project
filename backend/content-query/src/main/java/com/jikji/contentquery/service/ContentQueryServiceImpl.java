package com.jikji.contentquery.service;

import com.jikji.contentquery.domain.Content;
import com.jikji.contentquery.domain.ImageUrl;
import com.jikji.contentquery.exception.ContentNotFoundException;
import com.jikji.contentquery.repository.ContentQueryRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentQueryServiceImpl implements ContentQueryService {

	private final MongoTemplate mongoTemplate;
	private final ContentQueryRepository contentQueryRepository;


	public Page<Content> findByContentIdWithSize(Long userId, Long contentId, Integer size) {
		Pageable pageable = Pageable.ofSize(size);

		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		query.addCriteria(Criteria.where("contentId").lt(contentId));
		query.with(Sort.by(Sort.Direction.DESC, "contentId"));
		query.with(pageable);

		List<Content> contentList = mongoTemplate.find(query, Content.class);

		return PageableExecutionUtils.getPage(
			contentList,
			pageable,
			() -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Content.class));
	}

    public Content findByContentId(Long contentId) {
        return contentQueryRepository.findByContentId(contentId)
                .orElseThrow(ContentNotFoundException::new);
    }

    public List<Content> findByUserId(Long userId) {
        return contentQueryRepository.findByUserId(userId);
    }

    @Override
    public List<Content> findAllByContentIdIn(List<Long> contentIds) {
        return contentQueryRepository.findByContentIdIn(contentIds);
    }
}
