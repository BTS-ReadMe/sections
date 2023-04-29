package com.readme.sections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.readme.sections")
public class SectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SectionsApplication.class, args);
	}

}
