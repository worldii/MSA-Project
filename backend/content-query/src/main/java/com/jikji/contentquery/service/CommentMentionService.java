package com.jikji.contentquery.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jikji.contentquery.domain.Comment;
import com.jikji.contentquery.domain.CommentMention;
import com.jikji.contentquery.domain.User;
import com.jikji.contentquery.exception.CustomException;
import com.jikji.contentquery.exception.ErrorCode;
import com.jikji.contentquery.repository.CommentMentionRepository;
import com.jikji.contentquery.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentMentionService {
	private final CommentMentionRepository commentMentionRepository;
	private final UserRepository userRepository;

	@Transactional
	public void mentionMember(User user, Comment comment) {
		List<String> username = extractUsernameFromString(comment.getDescription());
		log.info("USERNAME 멘션" + username);

		// Refactoring 해야 함. User Server 에서 가져올 수 있게.
		List<User> allByUserName = userRepository.findByUserNameIn(username);

		for (int i = 0; i < username.size(); i++) {
			CommentMention commentMention = CommentMention.builder()
				.sender(user)
				.receiver(allByUserName.get(i))
				.comment(comment)
				.build();
			commentMentionRepository.save(commentMention);
		}
	}


	private List<String> extractUsernameFromString(String input) {
		final Set<String> mentions = new HashSet<>();
		final String regex = "@[0-9a-zA-Z가-힣ㄱ-ㅎ]+";
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			// 존재 여부 판단해야 함 ( from  User Service ) -> Refactoring
			userRepository.findByUserName(matcher.group().substring(1)).orElseThrow(()->new CustomException(ErrorCode.MENTION_USER_NOT_FOUND));
			mentions.add(matcher.group().substring(1));
		}
		return new ArrayList<>(mentions);
	}
}
