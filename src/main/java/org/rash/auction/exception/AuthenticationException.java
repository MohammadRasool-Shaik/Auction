/**
 *
 */
package org.rash.auction.exception;

import javax.ws.rs.core.Response;

/**
 * @author mshai9
 */
public class AuthenticationException extends BaseWebApplicationException {

    /**
     *
     */
    private static final long serialVersionUID = -405881832308743189L;

    public AuthenticationException() {
        super(Response.Status.UNAUTHORIZED.getStatusCode(), "Authentication Error. The username or password were incorrect");
    }

    public AuthenticationException(int errorStatusCode, String errorMessage) {
        super(errorStatusCode, errorMessage);
    }

    public AuthenticationException(String errorMessage) {
        super(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), errorMessage);
    }

}
