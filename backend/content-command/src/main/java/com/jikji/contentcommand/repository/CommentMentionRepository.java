package com.jikji.contentcommand.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jikji.contentcommand.domain.Comment;
import com.jikji.contentcommand.domain.CommentMention;

@Repository
public interface CommentMentionRepository extends JpaRepository<CommentMention, Long> {
	List<CommentMention> findAllByComment(Comment comment);
}
