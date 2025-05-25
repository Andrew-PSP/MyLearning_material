package com.pyae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pyae.base.BaseRepositoryImpl;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
