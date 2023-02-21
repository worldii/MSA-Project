package com.example.searchservice.repository;

import com.example.searchservice.domain.Hashtag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByName(String name);

    List<Hashtag> findAllByNameIn(List<String> names);
}
