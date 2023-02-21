package com.example.searchservice.controller;

import com.example.searchservice.service.HashtagService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hashtags")
public class HashtagController {

    private final HashtagService hashtagService;

    @PostMapping
    public ResponseEntity<?> addHashtags(@RequestBody Map<String, List<String>> hashtags) {
        final List<Long> result = hashtagService.addHashtag(hashtags.get("hashtags"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
