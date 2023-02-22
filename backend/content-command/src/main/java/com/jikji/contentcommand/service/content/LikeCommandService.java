package com.jikji.contentcommand.service.content;

public interface LikeCommandService {

    int likeContent(Long userId, Long contentId);

    int unlikeContent(Long userId, Long contentId);
}
