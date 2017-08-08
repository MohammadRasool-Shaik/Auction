/**
 * 
 */
package org.rash.auction.provider;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.rash.auction.exception.AuthenticationException;
import org.rash.auction.model.AuthorizationToken;
import org.rash.auction.repository.AuthorizationTokenRepository;

/**
 * @author mshai9
 * 
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