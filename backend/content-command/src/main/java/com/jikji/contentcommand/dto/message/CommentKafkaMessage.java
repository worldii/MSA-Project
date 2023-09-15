package com.jikji.contentcommand.dto.message;
import com.jikji.contentcommand.domain.Comment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentKafkaMessage {

	private Long commentId;
	private Long userId;
	private Long postId;
	private String userName;
	private String profileUrl;
	private String createdAt;
	private String description;


	public CommentKafkaMessage(Comment comment) {
		this.commentId = comment.getId();
		this.userId = comment.getUserId();
		this.postId = comment.getPostId();
		this.userName = comment.getUserName();
		this.profileUrl = comment.getProfileUrl();
		this.createdAt = comment.getCreatedAt();
		this.description = comment.getDescription();
	}
}


