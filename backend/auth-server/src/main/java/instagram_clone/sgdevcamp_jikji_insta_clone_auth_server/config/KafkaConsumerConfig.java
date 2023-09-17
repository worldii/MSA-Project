package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.kafka.dto.ChatEmail;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String servers;

	@Bean
	public ConsumerFactory<String, ChatEmail> consumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "jikji-project");

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
			new JsonDeserializer<>(ChatEmail.class,false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ChatEmail> kafkaListener() {
		ConcurrentKafkaListenerContainerFactory<String, ChatEmail> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}

