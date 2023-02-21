package com.example.searchservice.service;

import com.example.searchservice.domain.Hashtag;
import com.example.searchservice.repository.HashtagRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public List<Long> addHashtag(List<String> names) {
        names.forEach(name -> {
            log.info(name);
            Hashtag hashtag = hashtagRepository.findByName(name)
                    .orElse(new Hashtag(name));
            hashtag.increaseCount();
            hashtagRepository.save(hashtag);
        });
        final List<Hashtag> results = hashtagRepository.findAllByNameIn(names);
        return results.stream().map(Hashtag::getId)
                .collect(Collectors.toList());
    }
}
