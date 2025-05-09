package com.pyae.em.entity.listener;

import java.lang.System.Logger;
import java.time.LocalDateTime;

import com.pyae.em.entity.ActivityLogger;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class ListenerForLog {

	@PrePersist
	public void setCreation(Object entity) {
		if(entity instanceof ActivityLogger log) {
			log.setCreateAt(LocalDateTime.now());
		}
	}
	
	@PreUpdate
	public void setUpdate(Object entity) {
		if(entity instanceof ActivityLogger log) {
			log.setUpdateAt(LocalDateTime.now());
		}
	}
}
