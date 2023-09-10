package com.jikji.contentcommand.repository;

import java.util.List;
import java.util.Optional;

import com.jikji.contentcommand.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jikji.contentcommand.domain.CommentLikes;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
	Optional<CommentLikes> findByComment(Comment comment);
}
