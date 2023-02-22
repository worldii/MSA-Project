package com.jikji.contentcommand.service.content;

import com.jikji.contentcommand.domain.Content;
import com.jikji.contentcommand.dto.request.ContentUpdateRequest;

public interface ContentCommandService {

    Content save(final Content content);

    void update(final Long contentId, final ContentUpdateRequest request);

    void delete(final Long contentId);

    boolean visibilityLikes(Long contentId);

    boolean visibilityComments(Long contentId);
}
