package com.pyae.jpa.entity2;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Parent {

	private String name;
	private String occupation;
}
