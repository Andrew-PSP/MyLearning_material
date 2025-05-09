package com.pyae.em.entity;

import java.time.LocalDateTime;

import com.pyae.em.entity.listener.ListenerForLog;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
@EntityListeners(ListenerForLog.class)
public abstract class ActivityLogger {

	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}
