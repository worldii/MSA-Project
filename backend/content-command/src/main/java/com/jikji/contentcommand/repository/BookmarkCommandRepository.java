package com.jikji.contentcommand.repository;

import com.jikji.contentcommand.domain.Bookmark;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkCommandRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUserIdAndContentId(Long userId, Long contentId);

    Boolean existsByUserIdAndContentId(Long userId, Long contentId);
}
