/**
 * 
 */
package com.sapient.auction.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author mshai9
 *
 */
@Entity
@Table(indexes = { @Index(name = "IDX_BIDX1", columnList = "saleId,buyerId") })
public class Bid extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 8420190973311895143L;

	private Double amount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@ManyToOne
	@JoinColumn(name = "saleId")
	private Sale sale;

	@ManyToOne
	@JoinColumn(name = "buyerId")
	private User user;

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}

	/**
	 * @param sale
	 *            the sale to set
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
