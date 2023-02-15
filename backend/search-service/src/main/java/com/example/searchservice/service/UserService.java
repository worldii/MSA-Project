package com.example.searchservice.service;

import com.example.searchservice.dto.response.UserResponse;
import com.example.searchservice.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> findAllByKeyword(String keyword, Pageable pageable) {
        return userRepository.findAllByNameOrLoginIdUsingQuery(keyword, pageable)
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}
