/**
 * 
 */
package org.rash.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mshai9
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 197564124511341822L;

	public ProductNotFoundException(Integer productId) {
		super("No such product: " + productId);
	}
}
