package com.jikji.contentcommand.controller;

import com.jikji.contentcommand.dto.message.NotificationMessage;
import com.jikji.contentcommand.dto.request.ContentCreateRequest;
import com.jikji.contentcommand.dto.request.ContentUpdateRequest;
import com.jikji.contentcommand.exception.ContentNotFoundException;
import com.jikji.contentcommand.service.ContentCommandService;
import com.jikji.contentcommand.service.ContentSaver;
import com.jikji.contentcommand.infra.KafkaProducer;
import java.net.URI;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/contents")
public class ContentCommandController {

    private final ContentCommandService contentCommandService;
    private final KafkaProducer kafkaProducer;
    private final ContentSaver contentSaver;

    @PostMapping
    public ResponseEntity<Void> createContent(@RequestBody final ContentCreateRequest request) {
        Long savedId = contentSaver.save(request);

        NotificationMessage notificationMessage = NotificationMessage.builder()
            .senderId(request.getUserId().intValue())
            .type("post")
            .build();

        kafkaProducer.sendMessage(notificationMessage);

        return ResponseEntity.created(URI.create("/contents" + savedId)).build();
    }

    @PatchMapping("/{contentId}")
    public ResponseEntity<Long> updateContent(
        @RequestBody final ContentUpdateRequest request,
        @PathVariable Long contentId
    ) {
        try {
            contentCommandService.update(contentId, request);
            return ResponseEntity.ok(contentId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> deleteContent(
        @PathVariable Long contentId
    ) {
        contentCommandService.delete(contentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/visibility/likes/{contentId}")
    public ResponseEntity<Boolean> visibilityLikes(@PathVariable final Long contentId) {
        try {
            boolean result = contentCommandService.visibilityLikes(contentId);
            return ResponseEntity.ok(result);
        } catch (ContentNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/visibility/comments/{contentId}")
    public ResponseEntity<Boolean> visibilityComments(@PathVariable final Long contentId) {
        try {
            boolean result = contentCommandService.visibilityComments(contentId);
            return ResponseEntity.ok(result);
        } catch (ContentNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
