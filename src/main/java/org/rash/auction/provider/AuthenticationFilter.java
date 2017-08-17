/**
 *
 */
package org.rash.auction.provider;

import org.rash.auction.exception.AuthenticationException;
import org.rash.auction.model.AuthorizationToken;
import org.rash.auction.repository.AuthorizationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mshai9
 * <p>
 * The client sends their credentials (username and password) to the
 * server. The server authenticates the credentials and generates a
 * token. The server stores the previously generated token in some
 * storage along with the user identifier and an expiration date. The
 * server sends the generated token to the client. In every request, the
 * client sends the token to the server. The server, in each request,
 * extracts the token from the incoming request. With the token, the
 * server looks up the user details to perform authentication and
 * authorization. If the token is valid, the server accepts the request.
 * If the token is invalid, the server refuses the request. The server
 * can provide an endpoint to refresh expired tokens.
 */
//@Provider
//@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String ACCESS_BLOCKED_FOR_ALL_USERS = "Access blocked for all users !!";

    private static final String YOU_CANNOT_ACCESS_THIS_RESOURCE = "You cannot access this resource";

    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class.getName());

    @Context
    private ResourceInfo resourceInfo;

    @Autowired
    private AuthorizationTokenRepository authorizationTokenRepository;

	/*
     * * * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container .ContainerRequestContext)
	 */

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();
        // No Permit for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            // Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                throw new AuthenticationException(Response.Status.FORBIDDEN.getStatusCode(), ACCESS_BLOCKED_FOR_ALL_USERS);
            }
            // Get the HTTP Authorization header from the request
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            // Check if the HTTP Authorization header is present and formatted correctly
            if (authorizationHeader == null) {
                logger.error(YOU_CANNOT_ACCESS_THIS_RESOURCE);
                throw new AuthenticationException(Response.Status.NOT_ACCEPTABLE.getStatusCode(), YOU_CANNOT_ACCESS_THIS_RESOURCE);
            }
            isUserAllowed(method, authorizationHeader);
        }
    }

    private void isUserAllowed(Method method, String authorizationHeader) {
        // Extract the token from the HTTP Authorization header and Validate the token
        AuthorizationToken authorizationToken = authorizationTokenRepository.findByToken(authorizationHeader.trim());
        if (authorizationToken != null) {
            // Verify user access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
                // Is user valid?
                if (!rolesSet.contains(authorizationToken.getUser().getUserRole().getKey())) {
                    logger.error(YOU_CANNOT_ACCESS_THIS_RESOURCE);
                    throw new AuthenticationException(Response.Status.UNAUTHORIZED.getStatusCode(), YOU_CANNOT_ACCESS_THIS_RESOURCE);
                }
            }
        } else {
            // if Token is null
            logger.error(ACCESS_BLOCKED_FOR_ALL_USERS);
            throw new AuthenticationException(Response.Status.FORBIDDEN.getStatusCode(), ACCESS_BLOCKED_FOR_ALL_USERS);
        }
    }
}