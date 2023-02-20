package instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.service;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.chat.ChatMessage;
import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto.ChatEmail;
import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto.UserInfo;

@Service
public class KafkaConsumer {

	KafkaProducer kafkaProducer;

	public KafkaConsumer(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "jikji-project", containerFactory = "kafkaListener")
	public void consume(ChatMessage chatMessage) throws IOException {
			String sender = chatMessage.getSender();
			String receiver = chatMessage.getReceiver();
			String type = chatMessage.getType();
			ChatEmail chatEmail = ChatEmail.builder().senderEmail(sender).receiverEmail(receiver).type(type).build();
			kafkaProducer.sendMessage(chatEmail);
	}

	@KafkaListener(topics = "jikji-chat-info", groupId = "jikji-project", containerFactory = "kafkaUserListener")
	public void userConsume(UserInfo userInfo) throws IOException {
		Integer senderId = userInfo.getSenderId();
		String receiverEmail = userInfo.getReceiverEmail();
		String senderNickname = userInfo.getSenderNickname();
		//chat,follow, post, comment 서버 전부 email 형태로 줄 수 있는지
	}
}
