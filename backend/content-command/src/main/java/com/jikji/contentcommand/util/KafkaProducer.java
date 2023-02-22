package com.jikji.contentcommand.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.jikji.contentcommand.dto.message.NotificationMessage;

@Service
public class KafkaProducer {

	@Value(value = "jikji")
	private String topicName;

	private final KafkaTemplate<String, NotificationMessage> userKafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, NotificationMessage> userKafkaTemplate) {
		this.userKafkaTemplate = userKafkaTemplate;
	}

	public void sendMessage(NotificationMessage email) {
		userKafkaTemplate.send(topicName, email);
	}
}

