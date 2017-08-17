package org.rash.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application BootStart
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.rash.auction.model", "org.rash.auction.repository"})
@PropertySource("classpath:db-config.properties")
public class ApplicationBootStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootStart.class, args);
    }
}
