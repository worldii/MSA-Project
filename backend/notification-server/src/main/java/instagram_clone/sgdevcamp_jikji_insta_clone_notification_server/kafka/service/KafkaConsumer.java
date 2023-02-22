package instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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
import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.sse.NotificationService;

@Service
public class KafkaConsumer {

	KafkaProducer kafkaProducer;
	HttpHeaders httpHeaders;
	RestTemplate restTemplate;
	NotificationService notificationService;

	public KafkaConsumer(KafkaProducer kafkaProducer, HttpHeaders httpHeaders, RestTemplate restTemplate,
		NotificationService notificationService) {
		this.kafkaProducer = kafkaProducer;
		this.httpHeaders = httpHeaders;
		this.restTemplate = restTemplate;
		this.notificationService = notificationService;
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

			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			List object = restTemplate.getForObject(
				"http://localhost:8000/follow-service/user/{user_id}/follower",
				List.class, senderId.longValue());
			assert object != null;
			for (Object obj : object) {
				Map objMap = (Map)obj;
				String id = objMap.get("id").toString();
				ChatEmail chatEmail = ChatEmail.builder()
					.senderId(senderId)
					.receiverId(Integer.parseInt(id))
					.type(type)
					.build();

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
		notificationService.send(senderNickname,receiverId.toString(), type);
	}
}
