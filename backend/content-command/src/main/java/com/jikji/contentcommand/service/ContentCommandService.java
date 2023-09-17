package com.jikji.contentcommand.service;

import com.jikji.contentcommand.domain.Content;
import com.jikji.contentcommand.dto.request.ContentUpdateRequest;
import java.util.List;

public interface ContentCommandService {

    Long save(final Content content, List<String> tags);

    void update(final Long contentId, final ContentUpdateRequest request);

    void delete(final Long contentId);

    boolean visibilityLikes(Long contentId);

    boolean visibilityComments(Long contentId);
}
