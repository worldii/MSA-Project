package com.jikji.contentcommand.infra;

public final class KafkaTopic {

    public static final String ADD_CONTENT = "content-add";
    public static final String DELETE_CONTENT = "content-delete";
    public static final String UPDATE_CONTENT = "content-update";
    public static final String ADD_CONTENT_SEARCH = "content-search-add";

    public static final String ADD_BOOKMARK = "bookmark-add";
    public static final String DELETE_BOOKMARK = "bookmark-delete";

    public static final String VISIBLE_COMMENT = "comment-visible";
    public static final String VISIBLE_LIKES = "likes-visible";

    public static final String ADD_CONTENT_LIKE = "like-add";
    public static final String DELETE_CONTENT_LIKE = "like-delete";

    public static final String UPDATE_HASHTAG = "hashtag-update";

    public static final String ADD_COMMENT = "comment-add";
    public static final String DELETE_COMMENT = "comment-delete";

    public static final String ADD_COMMENT_LIKE = "comment-like-add";
    public static final String DELETE_COMMENT_LIKE = "comment-like-delete";

    public static final String INCREASE_COMMENT_LIKES = "comment-likes-increase";
    public static final String DECREASE_COMMENT_LIKES = "comment-likes-decrease";

}
