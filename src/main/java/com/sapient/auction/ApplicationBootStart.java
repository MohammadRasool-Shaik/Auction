package com.sapient.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application BootStart
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.sapient.auction")
@EnableJpaRepositories(basePackages = { "com.sapient.auction.model", "com.sapient.auction.repository" })
@PropertySource("classpath:db-config.properties")
public class ApplicationBootStart {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationBootStart.class, args);
	}
}
