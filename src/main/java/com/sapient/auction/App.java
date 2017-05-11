package com.sapient.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.sapient.auction.config.DBConfiguration;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@Import(DBConfiguration.class)
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
