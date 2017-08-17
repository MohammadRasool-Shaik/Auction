/**
 *
 */
package org.rash.auction.dto;

import java.io.Serializable;

/**
 * @author mshai9
 */
public class ValidationError implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2515638542935026541L;
    private String propertyName;
    private String propertyValue;
    private String message;

    /**
     * @return the propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @param propertyName the propertyName to set
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * @return the propertyValue
     */
    public String getPropertyValue() {
        return propertyValue;
    }

    /**
     * @param propertyValue the propertyValue to set
     */
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
