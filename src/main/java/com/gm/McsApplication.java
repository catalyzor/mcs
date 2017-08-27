package com.gm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class McsApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsApplication.class, args);
	}
}
