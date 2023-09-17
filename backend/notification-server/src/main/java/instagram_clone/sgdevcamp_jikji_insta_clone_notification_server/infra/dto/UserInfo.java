package instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	private Integer senderId;
	private String senderNickname;
	private Integer receiverId;
	private String receiverEmail;
	private String type;
}
