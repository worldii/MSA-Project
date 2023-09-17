package instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.mail.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.mail.domain.MailAuth;
import instagram_clone.sgdevcamp_jikji_insta_clone_auth_server.mail.repository.MailAuthRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailAuthService {

	private final MailAuthRepository mailAuthRepository;

	@Transactional
	public void register(MailAuth mailAuth){
		mailAuthRepository.save(mailAuth);
	}

	@Transactional
	public void deleteByEmail(String email){
		mailAuthRepository.deleteByEmail(email);
	}

	public MailAuth findByEmail(String email){
		return mailAuthRepository.findByEmail(email).orElse(null);
	}
}
