package instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.kafka.dto.ChatEmail;

@Service
public class KafkaProducer {

	@Value(value = "jikji-chat-email")
	private String topicName;

	private final KafkaTemplate<String, ChatEmail> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, ChatEmail> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(ChatEmail email) {
		kafkaTemplate.send(topicName, email);
	}
}
