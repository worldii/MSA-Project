package com.jikji.contentcommand.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationMessage {
	private Integer senderId;
	private Integer receiverId;
	private String type;
}


