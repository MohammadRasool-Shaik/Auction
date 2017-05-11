/**
 * 
 */
package com.sapient.auction.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sapient.auction.dto.ErrorResponse;

/**
 * @author mshai9
 *
 */
public abstract class BaseWebApplicationException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2590896484832295491L;
	private final Integer errorCode;
	private final String errorMessage;

	public BaseWebApplicationException(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	@Override
	public Response getResponse() {
		return Response.status(errorCode).type(MediaType.APPLICATION_JSON_TYPE).entity(new ErrorResponse(errorCode, errorMessage)).build();
	}

}
