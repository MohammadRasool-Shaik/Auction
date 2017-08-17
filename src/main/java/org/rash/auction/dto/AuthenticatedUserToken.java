package org.rash.auction.dto;

import java.io.Serializable;

/**
 * @author: mshai9
 */
public class AuthenticatedUserToken implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1386584105236519252L;
    private String email;
    private String token;
    private ResponseStatus status;

    public AuthenticatedUserToken() {
        super();
    }

    /**
     * @param email
     * @param token
     */
    public AuthenticatedUserToken(String email, String token) {
        super();
        this.email = email;
        this.token = token;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

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
     * @return the status
     */
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

}
