package org.rash.auction.dto;

import javax.validation.constraints.NotNull;

/**
 *
 * @author: mshai9
 */
public class LoginDTO {

	@NotNull
	private String email;

	@NotNull
	private String password;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
