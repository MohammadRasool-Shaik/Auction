/**
 * 
 */
package org.rash.auction.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

/**
 * @author mshai9
 *
 */
@Embeddable
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4535089965518194333L;
	private String street;
	private String city;
	private String state;

	@Pattern(regexp = "^[1-9][0-9]{5}", message = "Pincode should be in numeric")
	@Column(nullable = false)
	private String pincode;

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
