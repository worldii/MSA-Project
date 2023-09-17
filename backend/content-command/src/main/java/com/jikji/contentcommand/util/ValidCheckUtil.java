package com.jikji.contentcommand.util;

import com.jikji.contentcommand.exception.BookmarkDuplicatedException;
import com.jikji.contentcommand.exception.LikeDuplicatedException;
import com.jikji.contentcommand.repository.BookmarkCommandRepository;
import com.jikji.contentcommand.repository.LikeCommandRepository;

public final class ValidCheckUtil {
    public static void checkDuplicatedLike(
        final Long userId,
        final Long contentId,
        final LikeCommandRepository likeCommandRepository) {
        if (Boolean.TRUE.equals(likeCommandRepository.existsByUserIdAndContentId(userId, contentId))) {
            throw new LikeDuplicatedException();
        }
    }

    public static void checkDuplicatedBookmark(
        final Long userId, Long contentId,
        final BookmarkCommandRepository bookmarkCommandRepository
    ) {
        if (Boolean.TRUE.equals(bookmarkCommandRepository.existsByUserIdAndContentId(userId, contentId))) {
            throw new BookmarkDuplicatedException();
        }
    }
}
