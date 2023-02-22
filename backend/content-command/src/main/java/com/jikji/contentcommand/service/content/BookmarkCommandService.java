package com.jikji.contentcommand.service.content;

public interface BookmarkCommandService {

    Long saveBookmark(Long userId, Long contentId);

    void unsaveBookmark(Long userId, Long contentId);
}
