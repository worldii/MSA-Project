package com.jikji.commentserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jikji.commentserver.domain.Comment;
import com.jikji.commentserver.domain.CommentLikes;
import com.jikji.commentserver.domain.User;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
	Optional<CommentLikes> findByUserAndComment(User user, Comment comment);

	Optional<CommentLikes> findByUserIdAndCommentId(long userId, long commentId);

	List<CommentLikes> findAllByCommentId(long commentId);

	List<CommentLikes> findAllByComment(Comment comment);
}
