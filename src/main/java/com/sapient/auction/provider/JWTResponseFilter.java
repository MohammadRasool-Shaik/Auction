/**
 * 
 */
package com.sapient.auction.provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.auction.util.JWTokenUtility;

/**
 * @author mshai9
 * 
 */
@Provider
public class JWTResponseFilter implements ContainerResponseFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTResponseFilter.class.getName());

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		Method method = resourceInfo.getResourceMethod();
		if (!method.isAnnotationPresent(PermitAll.class)) {
			logger.info("response filter invoked...");

			List<Object> jwt = new ArrayList<Object>();
			jwt.add("Bearer " + JWTokenUtility.buildJWT("email1@mail.com"));
			// jwt.add(requestContext.getHeaderString("Authorization"));
			responseContext.getHeaders().put("jwt", jwt);
			logger.info("Added JWT to response header 'jwt'");
		}
	}

	

}