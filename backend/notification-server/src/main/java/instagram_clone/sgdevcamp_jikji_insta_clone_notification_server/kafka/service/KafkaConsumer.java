package instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.chat.ChatMessage;
import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto.ChatEmail;
import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto.UserDto;
import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto.UserDtoList;
import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto.UserInfo;

@Service
public class KafkaConsumer {

	KafkaProducer kafkaProducer;
	HttpHeaders httpHeaders;
	RestTemplate restTemplate;

	public KafkaConsumer(KafkaProducer kafkaProducer, HttpHeaders httpHeaders, RestTemplate restTemplate) {
		this.kafkaProducer = kafkaProducer;
		this.httpHeaders = httpHeaders;
		this.restTemplate = restTemplate;
	}

	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "jikji-project", containerFactory = "kafkaListener")
	public void consume(ChatMessage chatMessage) throws IOException {
		String type = chatMessage.getType();
		Integer senderId = chatMessage.getSenderId();
		if (Objects.equals(type, "chat") || Objects.equals(type, "follow")) {
			Integer receiverId = chatMessage.getReceiverId();
			ChatEmail chatEmail = ChatEmail.builder().senderId(senderId)
				.receiverId(receiverId).type(type).build();
			kafkaProducer.sendMessage(chatEmail);
		} else {
			httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			ResponseEntity<UserDtoList> entity = restTemplate.getForEntity(
				"http://localhost:8000/follow-service/user/{user_id}/follower",
				UserDtoList.class, senderId);
			List<UserDto> userDtoList = Objects.requireNonNull(entity.getBody()).getUserDtoList();
			for (UserDto userDto : userDtoList) {
				Integer receiverId = userDto.getId().intValue();
				ChatEmail chatEmail = ChatEmail.builder().senderId(senderId).receiverId(receiverId).type(type).build();
				kafkaProducer.sendMessage(chatEmail);
			}

		}

	}

	@KafkaListener(topics = "jikji-chat-info", groupId = "jikji-project", containerFactory = "kafkaUserListener")
	public void userConsume(UserInfo userInfo) throws IOException {
		Integer senderId = userInfo.getSenderId();
		String senderNickname = userInfo.getSenderNickname();
		Integer receiverId = userInfo.getReceiverId();
		String receiverEmail = userInfo.getReceiverEmail();
		String type = userInfo.getType();
	}
}
