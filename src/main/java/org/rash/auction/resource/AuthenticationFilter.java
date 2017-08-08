/**
 * 
 */
package org.rash.auction.resource;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.rash.auction.repository.AuthorizationTokenRepository;
import org.rash.auction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import org.rash.auction.model.AuthorizationToken;

/**
 * @author mshai9
 * 
 *         The client sends their credentials (username and password) to the
 *         server. The server authenticates the credentials and generates a
 *         token. The server stores the previously generated token in some
 *         storage along with the user identifier and an expiration date. The
 *         server sends the generated token to the client. In every request, the
 *         client sends the token to the server. The server, in each request,
 *         extracts the token from the incoming request. With the token, the
 *         server looks up the user details to perform authentication and
 *         authorization. If the token is valid, the server accepts the request.
 *         If the token is invalid, the server refuses the request. The server
 *         can provide an endpoint to refresh expired tokens.
 */
@Secured(value = { "" })
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Autowired
	private AuthorizationTokenRepository authorizationTokenRepository;

	@Autowired
	private UserRepository userRepository;

	private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
			.entity("You cannot access this resource").build();
	private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
			.entity("Access blocked for all users !!").build();

	/*
	 * * * (non-Javadoc)
	 * 
	 * @see
	 * javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container
	 * .ContainerRequestContext)
	 */

	@Override
	public void filter(ContainerRequestContext requestContext) {
		Method method = resourceInfo.getResourceMethod();
		if (!method.isAnnotationPresent(PermitAll.class)) {
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(ACCESS_FORBIDDEN);
				return;
			}
			// Get the HTTP Authorization header from the request
			String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

			// Check if the HTTP Authorization header is present and formatted
			// correctly
			if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
				throw new NotAuthorizedException("Authorization header must be provided");
			}

			// Extract the token from the HTTP Authorization header
			String token = authorizationHeader.substring("Bearer".length()).trim();

			AuthorizationToken authorizationToken = authorizationTokenRepository.findByToken(token);
			// Validate the token
			if (authorizationToken != null) {
				// Verify user access
				if (method.isAnnotationPresent(RolesAllowed.class)) {
					RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
					Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

					// Is user valid?
					if (!rolesSet.contains(authorizationToken.getUser().getUserRole().getKey())) {
						requestContext.abortWith(ACCESS_DENIED);
						return;
					}
				}
			} else {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}
		}
	}
}
