/**
 * 
 */
package com.sapient.auction.enumerator;

/**
 * @author mshai9
 *
 */
public enum UserRole {
	A("ADMIN", "Admin"), B("BUYER", "Buyer"), S("SELLER", "Seller");
	private String key;
	private String description;

	UserRole(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}

	public static UserRole value(String key) {
		for (UserRole userRole : UserRole.values()) {
			if (userRole.key.equals(key)) {
				return userRole;
			}
		}
		throw new IllegalArgumentException();
	}
}
