package com.example.searchservice.repository;

import com.example.searchservice.domain.UserIndex;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<UserIndex, String> {

    @Query(value = "{\"bool\": {\n"
            + "        \"should\": [\n"
            + "          {\"match_phrase\": {\"name\": \"?0\"}}, \n"
            + "          {\"match_phrase\": {\"nickname\": \"?0\"}}\n"
            + "        ]\n"
            + "      }}")
    List<UserIndex> findAllByNameOrLoginIdUsingQuery(String keyword, Pageable pageable);
}
