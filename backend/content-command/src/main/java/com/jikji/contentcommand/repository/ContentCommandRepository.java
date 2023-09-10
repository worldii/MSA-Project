package com.jikji.contentcommand.repository;

import com.jikji.contentcommand.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentCommandRepository extends JpaRepository<Content, Long> {
}
