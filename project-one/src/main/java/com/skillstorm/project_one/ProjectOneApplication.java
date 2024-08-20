package com.skillstorm.project_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectOneApplication {

	public static void main(String[] args) {
		System.out.println("Database Password: " + System.getenv("db-pwd"));

		SpringApplication.run(ProjectOneApplication.class, args);
	}

}
