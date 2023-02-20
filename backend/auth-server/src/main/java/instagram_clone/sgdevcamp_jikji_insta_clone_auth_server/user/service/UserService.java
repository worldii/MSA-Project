package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.dto.UserKafkaMessage;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.User;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserService {

	UserRepository userRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private final KafkaTemplate<String, String> kafkaTemplate;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
					   KafkaTemplate<String, String> kafkaTemplate) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Transactional
	public void register(User user){
		final User savedUser = userRepository.save(user);
		final UserKafkaMessage message = UserKafkaMessage.builder()
				.id(savedUser.getId())
				.name(savedUser.getName())
				.nickname(savedUser.getNickname())
				.createdAt(DateUtil.localdatetimeToString(savedUser.getCreateAt()))
				.build();

		sendMessage(message, "join-user");
	}

	public User findByEmail(String email){
		return userRepository.findByEmail(email).orElse(null);
	}

	public User findByNickname(String nickname){
		return userRepository.findByNickname(nickname).orElse(null);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	public List<User> findAll(){
		return userRepository.findAll();
	}
	@Transactional
	public void updateEmailAuth(String email){
		User user = userRepository.findByEmail(email).orElse(null);
		assert user != null;
		user.updateEmailAuth(true);
	}

	@Transactional
	public void updatePassword(String userEmail,String password){
		User user = userRepository.findByEmail(userEmail).get();
		String securePassword = bCryptPasswordEncoder.encode(password);
		user.updatePassword(securePassword);
	}

	@Transactional
	public void updateLoginAt(String userEmail){
		User user = userRepository.findByEmail(userEmail).get();
		user.updateLoginAt(LocalDateTime.now());
	}

	@Transactional
	public void updateUpdateAt(String userEmail){
		User user = userRepository.findByEmail(userEmail).get();
		user.updateUpdatedAt(LocalDateTime.now());
	}

	@Transactional
	public void updateNickname(String userEmail, String nickname){
		User user = userRepository.findByEmail(userEmail).get();
		user.updateNickname(nickname);
	}

	private void sendMessage(UserKafkaMessage message, String topic) {
		ObjectWriter writer = new ObjectMapper().writer();
		String data = null;

		try {
			data = writer.writeValueAsString(message);
			kafkaTemplate.send(topic, data);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		log.info("kafka send message: " + data);
	}
}
