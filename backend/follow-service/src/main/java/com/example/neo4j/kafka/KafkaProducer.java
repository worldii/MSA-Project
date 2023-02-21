package com.example.neo4j.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.neo4j.dto.chat.ChatMessage;

@Service
public class KafkaProducer {

	@Value(value = "${spring.kafka.template.default-topic}")
	private String topicName;

	private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(ChatMessage email) {
		kafkaTemplate.send(topicName, email);
	}
}
