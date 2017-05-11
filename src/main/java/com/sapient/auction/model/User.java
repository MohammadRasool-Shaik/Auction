/**
 * 
 */
package com.sapient.auction.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.DigestUtils;

import com.sapient.auction.enumerator.UserRole;
import com.sapient.auction.enumerator.UserStatus;

/**
 * @author mshai9
 *
 */
@Entity
public class User extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6272073593793343818L;

	@Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$", message = "Display Name min 3 chars, alowd alphabets and ., -, _")
	@Column(nullable = false)
	private String displayName;

	@Pattern(regexp = "[A-Za-z_.0-9-]+@{1}[a-z]+([.]{1}[a-z]{2,4})+", message = " Allowed alphabets (capital and small), 0 to 9, \".\", \"_\" and \"-\" before \"@\". Then exactly one \"@\" is allowed in whole email then small letters (1 or more) then multiple set of \".\" and alphabets (2 to 4)")
	@Column(unique = true, nullable = false)
	private String email;

//	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "")
	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private UserRole userRole;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private UserStatus status;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private AuthorizationToken authorizationToken;

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

	/**
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole
	 *            the userRole to set
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
	 * @return the status
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String hashPassword(String passwordToHash) {
		return DigestUtils.md5DigestAsHex(passwordToHash.getBytes());
	}

	/**
	 * @return the authorizationToken
	 */
	public AuthorizationToken getAuthorizationToken() {
		return authorizationToken;
	}

	/**
	 * @param authorizationToken
	 *            the authorizationToken to set
	 */
	public void setAuthorizationToken(AuthorizationToken authorizationToken) {
		this.authorizationToken = authorizationToken;
	}

	
}
