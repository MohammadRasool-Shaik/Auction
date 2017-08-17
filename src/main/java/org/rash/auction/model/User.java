/**
 *
 */
package org.rash.auction.model;

import org.rash.auction.enumerator.UserRole;
import org.rash.auction.enumerator.UserStatus;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.DigestUtils;

import javax.persistence.*;

/**
 * @author mshai9
 */
@Entity
public class User extends AbstractPersistable<Long> {

    /**
     *
     */
    private static final long serialVersionUID = -6272073593793343818L;

    @Column(nullable = false)
    private String displayName;

    @Column(unique = true, nullable = false)
    private String email;

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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
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
     * @param password the password to set
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
     * @param userRole the userRole to set
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
     * @param address the address to set
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
     * @param displayName the displayName to set
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
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String hashPassword(String passwordToHash) {
        return DigestUtils.md5DigestAsHex(passwordToHash.getBytes());
    }

}
