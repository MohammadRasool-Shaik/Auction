/**
 * 
 */
package com.sapient.auction.enumerator;

/**
 * @author mshai9
 *
 */
public enum SaleStatus {
	A("A", "Active"), I("I", "In-Active");
	private String key;
	private String description;

	SaleStatus(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static SaleStatus value(String key) {
		for (SaleStatus userStatus : SaleStatus.values()) {
			if (userStatus.key.equals(key)) {
				return userStatus;
			}
		}
		throw new IllegalArgumentException();
	}

}
