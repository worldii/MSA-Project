package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
	private Integer senderId;
	private String senderNickname;
	private Integer receiverId;
	private String type;
}