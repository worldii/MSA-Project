package com.example.chatservice.repository;

import com.example.chatservice.domain.ChatMessage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    Optional<ChatMessage> findTop1ByChatroomIdOrderByTimestampDesc(String chatroomId);

    List<ChatMessage> findAllByChatroomId(String chatroomId);

    void deleteByChatroomId(String chatroomId);
}
