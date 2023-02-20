package com.jikji.contentcommand.dto.message;

import com.jikji.contentcommand.domain.CommentMention;

public class CommentMentionKafkaMessage {
	private Long commentMentionId;
	private Long receiverId;
	private Long senderId;
	private Long commentId;

	public CommentMentionKafkaMessage(CommentMention commentMention) {
		this.commentMentionId = commentMention.getId();
		this.receiverId =commentMention.getReceiverId();
		this.senderId =commentMention.getSenderId();
		this.commentId = commentMention.getCommentId();
	}
}
