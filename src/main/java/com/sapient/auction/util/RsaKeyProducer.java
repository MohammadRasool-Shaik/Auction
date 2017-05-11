/**
 * 
 */
package com.sapient.auction.util;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mshai9
 *
 */
public class RsaKeyProducer {

	private static final Logger logger = LoggerFactory.getLogger(RsaKeyProducer.class.getName());

	private static RsaJsonWebKey rsaJsonWebKey;

	private RsaKeyProducer() {
	}

	/**
	 * 
	 * not an ideal implementation since does not implement double-lock synchronization check
	 */
	public static RsaJsonWebKey produce() {
		if (rsaJsonWebKey == null) {
			try {
				rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
			} catch (JoseException joseException) {
				logger.error("Got an error while generating RsaJsonWebKey"+joseException.getMessage());
			}
		}
		return rsaJsonWebKey;
	}
}
