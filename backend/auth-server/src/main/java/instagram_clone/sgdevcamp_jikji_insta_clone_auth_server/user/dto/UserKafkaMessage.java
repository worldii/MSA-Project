package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserKafkaMessage {

    private Integer id;

    private String name;

    private String nickname;

    private String createdAt;
}
