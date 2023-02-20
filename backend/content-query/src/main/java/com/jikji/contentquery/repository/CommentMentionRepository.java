package com.jikji.contentquery.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jikji.contentquery.domain.CommentMention;

@Repository
public interface CommentMentionRepository extends MongoRepository<CommentMention, Long> {
	List<CommentMention> findAllByCommentId(Long commentId);
}
