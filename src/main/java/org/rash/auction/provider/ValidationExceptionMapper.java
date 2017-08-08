/**
 * 
 */
package org.rash.auction.provider;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.rash.auction.dto.ErrorResponse;
import org.rash.auction.exception.ValidationException;

/**
 * @author mshai9
 *
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationExceptionMapper.class.getName());

	@Override
	public Response toResponse(ValidationException exception) {

		ErrorResponse errorResponse = new ErrorResponse(Response.Status.PRECONDITION_FAILED.getStatusCode(), exception.getMessage());

		logger.error("Internal Server Error: " + exception);
		logger.error("Internal Server Error: " + exception.getCause());

		return Response.status(errorResponse.getErrorCode()).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
	}

}
