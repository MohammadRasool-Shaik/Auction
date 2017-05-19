/**
 * 
 */
package com.sapient.auction.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author mshai9
 *
 */
@Configuration
public class JerseyInitialization extends ResourceConfig {
	public JerseyInitialization() {
		this.packages("com.sapient.auction.resource").packages("com.sapient.auction.provider");
	}
}
