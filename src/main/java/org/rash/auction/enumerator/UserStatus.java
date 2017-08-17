/**
 *
 */
package org.rash.auction.enumerator;

/**
 * @author mshai9
 */
public enum UserStatus {
    A("A", "Active"), I("I", "In-Active");
    private String key;
    private String description;

    UserStatus(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public static UserStatus value(String key) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.key.equals(key)) {
                return userStatus;
            }
        }
        throw new IllegalArgumentException();
    }

}
