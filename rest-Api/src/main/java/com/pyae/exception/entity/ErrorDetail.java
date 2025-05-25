package com.pyae.exception.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {

	private LocalDateTime ErrorAt;
	private String message;
	private String description;

}
