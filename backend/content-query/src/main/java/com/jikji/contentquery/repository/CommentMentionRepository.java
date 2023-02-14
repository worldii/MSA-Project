package com.jikji.contentquery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.CommentMention;

@Repository
public interface CommentMentionRepository extends JpaRepository<CommentMention, Long> {
	List<CommentMention> findAllByComment(Comment comment);
}
