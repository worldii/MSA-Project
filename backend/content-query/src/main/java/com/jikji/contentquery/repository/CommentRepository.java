package com.jikji.contentquery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jikji.contentquery.domain.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Long> {
	List<Comment> findAllByPostId(Long postId);

	Optional<Comment> findByCommentId(Long commentId);
}
