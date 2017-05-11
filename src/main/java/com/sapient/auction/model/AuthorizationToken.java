/**
 * 
 */
package com.sapient.auction.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Admin
 *
 */
@Entity
public class AuthorizationToken extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4083511667438125394L;

	private final static Integer DEFAULT_TIME_TO_LIVE_IN_SECONDS = (60 * 60 * 24 * 30); // 30

	@Column(length = 36)
	private String token;

	private Date timeCreated;

	private Date expirationDate;

	@JoinColumn(name = "user_id")
	@OneToOne
	private User user;

	public AuthorizationToken() {
	}

	public AuthorizationToken(User user) {
		this(user, DEFAULT_TIME_TO_LIVE_IN_SECONDS);
	}

	public AuthorizationToken(User user, Integer timeToLiveInSeconds) {
		this.token = UUID.randomUUID().toString();
		this.user = user;
		this.timeCreated = new Date();
		this.expirationDate = new Date(System.currentTimeMillis() + (timeToLiveInSeconds * 1000L));
	}

	public boolean hasExpired() {
		return this.expirationDate != null && this.expirationDate.before(new Date());
	}

	public String getToken() {
		return token;
	}

	public User getUser() {
		return user;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = new Date(System.currentTimeMillis() + (DEFAULT_TIME_TO_LIVE_IN_SECONDS * 1000L));
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = UUID.randomUUID().toString();
	}

	/**
	 * @param timeCreated
	 *            the timeCreated to set
	 */
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = new Date();
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
