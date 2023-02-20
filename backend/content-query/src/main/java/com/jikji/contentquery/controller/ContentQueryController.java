package com.jikji.contentquery.controller;

import com.jikji.contentquery.domain.Content;
import com.jikji.contentquery.service.ContentQueryService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContentQueryController {

	private final ContentQueryService contentQueryService;

	@GetMapping("/slicing")
	public ResponseEntity<Page<Content>> findByContentIdWithSize(@RequestParam(name = "u") Long userId,
		@RequestParam(name = "c") Long contentId, @RequestParam Integer size) {
		try {
			Page<Content> contents = contentQueryService.findByContentIdWithSize(userId, contentId, size);
			return ResponseEntity.ok(contents);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}

    @GetMapping("/contents/{contentId}")
    public ResponseEntity<?> findByContentId(@PathVariable Long contentId) {
        try {
            Content content = contentQueryService.findByContentId(contentId);
            return ResponseEntity.ok(content);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/contents/all")
    public ResponseEntity<?> findAllByContentIds(@RequestParam(name = "ids") List<Long> contentIds) {
        log.info("content ids:" + contentIds);
        List<Content> result = contentQueryService.findAllByContentIdIn(contentIds);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Content>> findByUserId(@PathVariable Long userId) {
        try {
            List<Content> contents = contentQueryService.findByUserId(userId);
            return ResponseEntity.ok(contents);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


