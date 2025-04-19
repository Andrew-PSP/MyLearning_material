package com.pyae.event;

import java.time.LocalDateTime;

public record MyEvent(String Message,LocalDateTime publishedAt) {
	
	public MyEvent(String Message) {
		this(Message, LocalDateTime.now());
	}
}
