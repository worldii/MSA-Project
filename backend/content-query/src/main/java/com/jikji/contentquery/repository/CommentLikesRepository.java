package com.jikji.contentquery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.CommentLikes;
import com.jikji.contentquery.domain.User;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
	Optional<CommentLikes> findByUserAndComment(User user, Comment comment);

	Optional<CommentLikes> findByUserIdAndCommentId(long userId, long commentId);

	List<CommentLikes> findAllByCommentId(long commentId);

	List<CommentLikes> findAllByComment(Comment comment);
}
