package com.jikji.contentcommand.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jikji.contentcommand.domain.CommentLikes;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
	Optional<CommentLikes> findByUserIdAndCommentId(Long userId,Long  commentId);
	List<CommentLikes> findAllByCommentId(Long commentId);
}
