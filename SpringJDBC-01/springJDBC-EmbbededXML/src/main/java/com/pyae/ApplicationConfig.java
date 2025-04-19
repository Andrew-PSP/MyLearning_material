package com.pyae;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("com.pyae.dao")
public class ApplicationConfig {

	@Bean
	EmbeddedDatabase embeddedDatabase() {
		return new EmbeddedDatabaseBuilder().addScript("classpath:/schema.sql").setType(EmbeddedDatabaseType.H2).build();
	}
}
