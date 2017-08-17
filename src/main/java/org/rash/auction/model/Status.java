package org.rash.auction.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

/**
 * @author mshai9
 */
@JsonRootName(value = "status")
public class Status implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6745929544504143684L;
    private Integer statusCode;
    private String statusMsg;

    private String link;

    public Status() {
        super();
    }

    /**
     * @param statusCode
     */
    public Status(Integer statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public Status(Integer statusCode, String statusMsg) {
        super();
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

}