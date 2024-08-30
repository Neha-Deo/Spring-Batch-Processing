package com.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchApplication {

	public static void main(String[] args) {

		System.exit(SpringApplication.exit(SpringApplication.run(SpringBatchApplication.class, args)));
	}

}
