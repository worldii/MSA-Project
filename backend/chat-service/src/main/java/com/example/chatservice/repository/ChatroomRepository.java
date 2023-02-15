package com.example.chatservice.repository;

import com.example.chatservice.domain.Chatroom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatroomRepository extends MongoRepository<Chatroom, String> {

    @Query(value = "{ '$or': [ {\"users.userA._id\": ?0}, {\"users.userB._id\": ?0} ] }")
    List<Chatroom> findAllByUsers(Long userId);

    @Query(value = "{ '$and': [ {\"users.userA._id\": ?0}, {\"users.userB._id\": ?1} ] }", count = true)
    Integer countAllByUsers(Long userA, Long userB);

    @Query(value = "{ '$and': [ {\"users.userA._id\": ?0}, {\"users.userB._id\": ?1} ] }")
    Optional<Chatroom> findByUserIds(Long userA, Long userB);

    @Query(value = "{ '$or': [ {\"users.userA._id\": ?0}, {\"users.userB._id\": ?0}], '_id': ?1 }")
    Optional<Chatroom> findByUserIdAndId(Long userId, String chatroomId);
}
