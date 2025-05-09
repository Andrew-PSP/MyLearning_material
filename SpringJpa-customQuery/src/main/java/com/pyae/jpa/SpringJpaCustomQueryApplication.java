package com.pyae.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pyae.jpa.base.BaseRepository;
import com.pyae.jpa.base.BaseRepositoryImpl;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class SpringJpaCustomQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaCustomQueryApplication.class, args);
	}

}
