package com.bugtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BugTrackerApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BugTrackerApplication.class);

	public static void main(String[] args) {
		LOGGER.debug("Hey There !!! Bug Tracking Application Started Loading ... !!!");
		SpringApplication.run(BugTrackerApplication.class, args);
		LOGGER.info("BUG-TRACKING APPLICATION IS RUNNING NOW !!!");
	}

}
