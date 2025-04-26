package com.havenora.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HavenoraApplication {
	private static final Logger logger = LoggerFactory.getLogger(HavenoraApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Havenora Application...");
		SpringApplication.run(HavenoraApplication.class, args);
		logger.info("Havenora is running now....");
	}
}

