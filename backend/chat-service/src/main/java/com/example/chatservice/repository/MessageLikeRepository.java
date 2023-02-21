package com.example.chatservice.repository;

import com.example.chatservice.domain.MessageLike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageLikeRepository extends MongoRepository<MessageLike, String> {

    Boolean existsByMessageIdAndUserId(String messageId, Long userId);

    void deleteByMessageIdAndUserId(String messageId, Long userId);

}
