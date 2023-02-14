package com.jikji.contentcommand.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String userName;
	private String fullName;
	private String profilePicture;
}
