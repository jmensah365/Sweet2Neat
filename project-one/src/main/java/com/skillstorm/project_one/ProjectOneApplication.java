package com.skillstorm.project_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectOneApplication {

	public static void main(String[] args) {
		System.out.println("Database URL: " + System.getenv("DB_URL"));
		System.out.println("Database Username: " + System.getenv("DB_USERNAME"));
		System.out.println("Database Password: " + System.getenv("DB_PWD"));

		SpringApplication.run(ProjectOneApplication.class, args);
	}
}
