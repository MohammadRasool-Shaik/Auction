/**
 * 
 */
package com.sapient.auction.provider;

import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.auction.exception.AuthenticationException;
import com.sapient.auction.util.JWTPrincipal;
import com.sapient.auction.util.JWTSecurityContext;
import com.sapient.auction.util.JWTokenUtility;

/**
 * @author mshai9
 * 
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTRequestFilter implements ContainerRequestFilter {

	private static final String ACCESS_BLOCKED_FOR_ALL_USERS = "Access blocked for all users !!";

	private static final String YOU_CANNOT_ACCESS_THIS_RESOURCE = "You cannot access this resource";

	private static final Logger logger = LoggerFactory.getLogger(JWTRequestFilter.class.getName());

	@Context
	private ResourceInfo resourceInfo;

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
			if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
				logger.error(YOU_CANNOT_ACCESS_THIS_RESOURCE);
				throw new AuthenticationException(Response.Status.NOT_ACCEPTABLE.getStatusCode(), YOU_CANNOT_ACCESS_THIS_RESOURCE);
			} else {
				String token = authorizationHeader.substring("Bearer".length()).trim();
				try {
					final JwtClaims claims = JWTokenUtility.validateTokenClaims(token);

					final JWTPrincipal principal = JWTokenUtility.buildPrincipal(claims);

					final SecurityContext securityContext = requestContext.getSecurityContext();

					JWTSecurityContext jwSecurityContext = new JWTSecurityContext(principal, securityContext.isSecure());
					requestContext.setSecurityContext(jwSecurityContext);

				} catch (InvalidJwtException invalidJwtException) {
					logger.error(invalidJwtException.getMessage() + " " + invalidJwtException.getStackTrace());
					throw new AuthenticationException(Response.Status.UNAUTHORIZED.getStatusCode(), invalidJwtException.getMessage());
				}
			}
		}
	}

}