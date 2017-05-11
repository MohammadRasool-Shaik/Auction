package com.sapient.auction.exception;

import javax.ws.rs.WebApplicationException;

public class AuthenticationException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239640888192844602L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(int statusCode, String accessBlockedForAllUsers) {
	}
}
