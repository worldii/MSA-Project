package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.dto;

import javax.persistence.Column;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfoDto {
	private Integer id;
	private String email;
	private String name;
	private String nickname;
	private String phone;
	private String profile;
}
