package com.sapient.auction.util;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.SecurityContext;

public class JWTSecurityContext implements SecurityContext {

	private JWTPrincipal principal;

	private boolean isSecure;

	private Set<String> roles = new HashSet<>();

	/**
	 * @param principal
	 * @param isSecure
	 * @param roles
	 */
	public JWTSecurityContext(final JWTPrincipal principal, final boolean isSecure) {
		super();
		this.principal = principal;
		this.isSecure = isSecure;
		roles.addAll(Arrays.asList(principal.getRoles()));
	}

	@Override
	public Principal getUserPrincipal() {
		return principal;
	}

	@Override
	public boolean isUserInRole(String role) {
		return roles.contains(role);
	}

	@Override
	public boolean isSecure() {
		return isSecure;
	}

	@Override
	public String getAuthenticationScheme() {
		return "JWT";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JWTSecurityContext {").append("principal:").append(principal).append(",").append("roles:").append(roles).append(",").append("isSecure:").append(isSecure)
				.append("}");
		return builder.toString();
	}

}
