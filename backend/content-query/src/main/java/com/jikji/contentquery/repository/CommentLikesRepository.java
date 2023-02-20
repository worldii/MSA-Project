package com.jikji.contentquery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jikji.contentquery.domain.CommentLikes;

@Repository
public interface CommentLikesRepository extends MongoRepository<CommentLikes, Long> {
	Optional<CommentLikes> findByUserIdAndCommentId(Long userId,Long  commentId);
	List<CommentLikes> findAllByCommentId(Long commentId);
	Optional<CommentLikes> findByCommentLikeId(Long commentId);
}
