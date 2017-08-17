/**
 *
 */
package org.rash.auction.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.UUID;

/**
 * @author mshai9
 */
@Entity
public class AuthorizationToken extends AbstractPersistable<Long> {

    /**
     *
     */
    private static final long serialVersionUID = -4083511667438125394L;

    private static final Integer DEFAULT_TIME_TO_LIVE_IN_SECONDS = 60 * 60 * 1; // 1
    // hour
    @Column(length = 36)
    private String token;

    private Date timeCreated;

    private Date expirationDate;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    public AuthorizationToken() {
        super();
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
        return this.expirationDate != null && this.expirationDate.after(this.timeCreated);
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
}
