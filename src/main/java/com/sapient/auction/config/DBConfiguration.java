/**
 * 
 */
package com.sapient.auction.config;

import java.util.logging.Logger;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author mshai9
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.sapient.auction")
@EnableJpaRepositories(basePackages = { "com.sapient.auction.model", "com.sapient.auction.repository" })
@PropertySource("classpath:db-config.properties")
public class DBConfiguration {
	protected Logger logger;

	public DBConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

}
