package kafka.practice.service;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import instagram_clone.sgdevcamp_jikji_insta_clone_notification_server.chat.ChatMessage;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "jikji",containerFactory = "kafkaListener")
	public void consume(ChatMessage message) throws IOException {
		System.out.println("message.getSender() = " + message.getSender());
		System.out.println("message.getContext() = " + message.getContext());
	}
}
