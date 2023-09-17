package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.kafka.service;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.kafka.dto.ChatEmail;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.kafka.dto.UserInfo;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.User;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.service.UserService;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

	private final UserService userService;
	private final KafkaProducer kafkaProducer;

	@KafkaListener(topics = "jikji-chat-email", groupId = "jikji-project", containerFactory = "kafkaListener")
	public void consume(ChatEmail email) throws IOException {
		Integer senderId = email.getSenderId();
		Integer receiverId = email.getReceiverId();
		String type = email.getType();
		User sender = userService.findById(senderId);
		UserInfo userInfo = UserInfo.builder()
			.senderId(senderId)
			.receiverId(receiverId)
			.senderNickname(sender.getNickname())
			.type(type)
			.build();
		kafkaProducer.sendMessage(userInfo);
	}
}

