package com.jikji.contentcommand.controller;

import com.jikji.contentcommand.service.LikeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeCommandController {

    private final LikeCommandService likeCommandService;

    @PostMapping("{userId}/like/{contentId}")
    public ResponseEntity<?> likeContent(@PathVariable final Long userId, @PathVariable final Long contentId) {
        try {
            return new ResponseEntity<>(likeCommandService.likeContent(userId, contentId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{userId}/unlike/{contentId}")
    public ResponseEntity<?> unlikeContent(@PathVariable final Long userId, @PathVariable final Long contentId) {
        try {
            int count = likeCommandService.unlikeContent(userId, contentId);
            return new ResponseEntity<>(count, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
