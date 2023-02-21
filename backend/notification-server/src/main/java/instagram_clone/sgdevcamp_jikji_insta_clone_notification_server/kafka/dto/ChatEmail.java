package instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatEmail {
	private Integer senderId;
	private Integer receiverId;
	private String type;
}
