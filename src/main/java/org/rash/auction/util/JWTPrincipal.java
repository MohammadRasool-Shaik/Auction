/**
 * 
 */
package org.rash.auction.util;

import java.security.Principal;
import java.util.Arrays;

/**
 * @author mshai9
 *
 */
public class JWTPrincipal implements Principal {

	private String name;
	private String displayName;
	private String[] roles;

	/**
	 * 
	 */
	public JWTPrincipal() {
	}

	public JWTPrincipal(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the roles
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JWTPrincipal {").append("name:").append(name).append(",").append(",").append("displayName:").append(displayName).append(",").append(",").append("roles:")
				.append(Arrays.toString(roles)).append("}");
		return builder.toString();
	}

}
