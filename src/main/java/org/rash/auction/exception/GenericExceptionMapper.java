/**
 * 
 */
package org.rash.auction.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.rash.auction.model.Status;

/**
 * @author mshai9
 *
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<AuthenticationException> {
	@Override
	public Response toResponse(AuthenticationException ex) {

		Status errorMessage = new Status();
		setHttpStatus(ex, errorMessage);
		errorMessage.setStatusMsg(ex.getMessage());

		return Response.status(errorMessage.getStatusCode())
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

	private void setHttpStatus(Throwable ex, Status errorMessage) {
		if(ex instanceof WebApplicationException ) {
			errorMessage.setStatusCode(((WebApplicationException)ex).getResponse().getStatus());
		} else {
			errorMessage.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); //defaults to internal server error 500
		}
	}
}
