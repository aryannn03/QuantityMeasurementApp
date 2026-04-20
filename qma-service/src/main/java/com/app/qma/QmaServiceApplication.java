package com.app.qma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QmaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QmaServiceApplication.class, args);
	}

}
