package org.rash.auction.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

/**
 * @author mshai9
 */
@JsonRootName(value = "responseStatus")
public class ResponseStatus implements Serializable {

    private static final long serialVersionUID = 6745929544504143684L;
    private Integer statusCode;
    private String statusMsg;

    public ResponseStatus() {
        super();
    }

    /**
     * @param statusCode
     */
    public ResponseStatus(Integer statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public ResponseStatus(Integer statusCode, String statusMsg) {
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

}