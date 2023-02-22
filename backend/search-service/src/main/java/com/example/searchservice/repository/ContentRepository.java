package com.example.searchservice.repository;

import com.example.searchservice.domain.ContentIndex;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.CountQuery;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ContentRepository extends ElasticsearchRepository<ContentIndex, String> {

    @Query(value = "{\"bool\": {\"must\": [{\"term\": {\"hashtags\": \"?0\"}}]}}")
    List<ContentIndex> findAllByHashtagUsingQuery(String hashtag, Pageable pageable);

    @CountQuery(value = "{\"term\": {\"hashtags\": \"?0\"}}")
    Long countByHashtagsUsingQuery(String hashtag);
}
