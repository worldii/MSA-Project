package com.jikji.contentcommand.service.comment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jikji.contentcommand.domain.CommentMention;
import com.jikji.contentcommand.dto.request.CommentCreateDto;
import com.jikji.contentcommand.repository.CommentMentionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentMentionService {
	private final KafkaTemplate<String, String> kafkaTemplate;

	private final CommentMentionRepository commentMentionRepository;

	@Transactional
	public void mentionMember(Long userId, String description) {
		List<String> username = extractUsernameFromString(description);
		// API 요청으로 username 에 해당하는 user id 가져옴. 알림 보내야 함.
		for (int i = 0; i < username.size(); i++) {
			// receiver id 수정해야함.
			CommentMention commentMention = CommentMention.builder().receiverId(userId)
				.senderId(userId).build();
			commentMentionRepository.save(commentMention);
		}
	}

	@Transactional
	public void deleteMentionAll(Long commentId) {
		List<CommentMention> allByComment = commentMentionRepository.findAllByCommentId(commentId);
		commentMentionRepository.deleteAll(allByComment);
	}

	private List<String> extractUsernameFromString(String input) {
		final Set<String> mentions = new HashSet<>();
		final String regex = "@[0-9a-zA-Z가-힣ㄱ-ㅎ]+";
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			// 존재 여부 판단해야 함 ( from  User Service ) -> Refactoring
			mentions.add(matcher.group().substring(1));
		}
		return new ArrayList<>(mentions);
	}
}
