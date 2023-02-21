package com.jikji.contentcommand.client;

import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "http://localhost:8000/search-service")
public interface HashtagFeignClient {

    @PostMapping("/hashtags")
    ResponseEntity<List<Long>> addHashtag(@RequestBody Map<String, List<String>> hashtags);
}
