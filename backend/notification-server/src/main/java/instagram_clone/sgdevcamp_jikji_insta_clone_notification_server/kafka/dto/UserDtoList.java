package instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDtoList {
	private List<UserDto> userDtoList;
}
