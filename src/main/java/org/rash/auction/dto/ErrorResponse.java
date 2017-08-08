/**
 * 
 */
package org.rash.auction.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author mshai9
 *
 */
@JsonRootName(value = "responseStatus")
public class ErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7161154068801883350L;

	private Integer errorCode;
	private String errorMessage;
	@JsonProperty("validationErrors")
	private List<ValidationError> validationErrors = new ArrayList<>();

	/**
	 * 
	 */
	public ErrorResponse() {
		super();
	}

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ErrorResponse(Integer errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the validationErrors
	 */
	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	/**
	 * @param validationErrors
	 *            the validationErrors to set
	 */
	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

}
