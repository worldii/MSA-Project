package com.jikji.contentquery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jikji.contentquery.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByUserNameIn(List<String> username);

	Optional<User> findByUserName(String username);
}
