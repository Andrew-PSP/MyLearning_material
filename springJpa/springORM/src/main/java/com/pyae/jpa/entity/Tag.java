package com.pyae.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Tag {

	private String name;
	private String logo;
}
