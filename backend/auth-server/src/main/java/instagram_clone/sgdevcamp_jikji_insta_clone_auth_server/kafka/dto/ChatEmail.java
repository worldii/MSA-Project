package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatEmail {
	private Integer senderId;
	private Integer receiverId;
	private String type;
}

