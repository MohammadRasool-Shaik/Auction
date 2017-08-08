/**
 * 
 */
package org.rash.auction.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author mshai9
 *
 */
@Configuration
public class JerseyInitialization extends ResourceConfig {
	public JerseyInitialization() {
		this.packages("org.rash.auction.resource").packages("org.rash.auction.provider");
	}
}
