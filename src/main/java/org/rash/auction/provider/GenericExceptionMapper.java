/**
 *
 */
package org.rash.auction.provider;

import org.rash.auction.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author mshai9
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private static Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class.getName());

    /*
     * (non-Javadoc)
     *
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(Throwable exception) {
        Response.StatusType statusType = getStatusType(exception);
        ErrorResponse errorResponse = new ErrorResponse(statusType.getStatusCode(), statusType.getReasonPhrase());
        logger.error("Internal Server Error: " + exception);
        logger.error("Internal Server Error: " + exception.getCause());
        return Response.status(statusType.getStatusCode()).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }

    private Response.StatusType getStatusType(Throwable exception) {
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse().getStatusInfo();
        } else {
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }

}
