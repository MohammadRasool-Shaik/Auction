package org.rash.auction.exception;

import javax.ws.rs.WebApplicationException;

/**
 * @author mshai9
 *
 */
public class NotFoundException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6563539532452407353L;

	public NotFoundException() {
		super(404);
	}

	public NotFoundException(String message) {
		super(message, 404);
	}

}
