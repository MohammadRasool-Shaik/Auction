/**
 * 
 */
package org.rash.auction.model;

import java.io.Serializable;

/**
 * @author mshai9
 *
 */
public class Credentials implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7918879939705807304L;

	private String userName;

	private String password;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
